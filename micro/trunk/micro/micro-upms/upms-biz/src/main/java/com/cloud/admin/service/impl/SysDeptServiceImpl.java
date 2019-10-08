

package com.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.dto.DeptTree;
import com.cloud.admin.api.entity.SysDept;
import com.cloud.admin.api.entity.SysDeptRelation;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.admin.mapper.SysDeptMapper;
import com.cloud.admin.service.SysDeptRelationService;
import com.cloud.admin.service.SysDeptService;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author ygnet
 * @since 2018-01-20
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
	private final SysDeptRelationService sysDeptRelationService;

	/**
	 * 添加信息部门
	 *
	 * @param dept 部门
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveDept(SysDept dept) {
		CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(dept, sysDept);
		this.save(sysDept);
		sysDeptRelationService.insertDeptRelation(sysDept);
		cacheManager.getCache("tree_details").clear();
		return Boolean.TRUE;
	}


	/**
	 * 删除部门
	 *
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeDeptById(String id) {
		CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
		//级联删除部门
		List<String> idList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor, id))
				.stream()
				.map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());

		if (CollUtil.isNotEmpty(idList)) {
			this.removeByIds(idList);
		}

		//删除部门级联关系
		sysDeptRelationService.deleteAllDeptRealtion(id);
		cacheManager.getCache("tree_details").clear();
		return Boolean.TRUE;
	}

	/**
	 * 更新部门
	 *
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateDeptById(SysDept sysDept) {
		CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
		//更新部门状态
		this.updateById(sysDept);
		//更新部门关系
		SysDeptRelation relation = new SysDeptRelation();
		relation.setAncestor(sysDept.getParentId());
		relation.setDescendant(sysDept.getDeptId());
		sysDeptRelationService.updateDeptRealtion(relation);
		//清空tree_details
		cacheManager.getCache("tree_details").clear();
		return Boolean.TRUE;
	}

	@Override
	public int hasChildren(String id) {
		return baseMapper.hasChildren(id);
	}

	@Override
	public List<DeptTree> asyncTree(SysDept sysDept) {
		CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
		Cache cache = cacheManager.getCache("tree_details");
		List<DeptTree> list = null;
		List<SysDept> depts = new ArrayList<>();
		if (sysDept.getDeptId()==null || sysDept.getDeptId()==""){
			sysDept.setDeptId("7B038CBFE0FIC09CE0530A282E21C09C");
			String kye = (sysDept.getDeptId());
			Object obj = cache.get(kye);
			if(obj!=null){
				list = (List<DeptTree>) (((SimpleValueWrapper) obj).get());
			}
			if(CollectionUtils.isEmpty(list)){
				List<DeptTree> treeList = new ArrayList<>();
				depts = baseMapper.asyncTree(sysDept);
				for (SysDept dept:depts){
					List<SysDept> nodes = baseMapper.asyncTree(dept);
                    treeList.add(nodeSetList(dept));
					for (SysDept deptss:nodes){
                        treeList.add(nodeSetList(deptss));
					}
				}
				list = TreeUtil.bulid(treeList, "0");
				cache.put(kye, list);
			}

		}else{
			String kye = (sysDept.getDeptId());
			Object obj = cache.get(kye);
			if(obj!=null){
				list = (List<DeptTree>) (((SimpleValueWrapper) obj).get());
			}
			if(CollectionUtils.isEmpty(list)){
				list = getTreeList(baseMapper.asyncTree(sysDept));
				cache.put(kye, list);
			}
		}
		return list;
	}

	private List<DeptTree> getTreeList(List<SysDept> depts) {
		List<DeptTree> treeList = new ArrayList<>();
		for (SysDept dept : depts){
            treeList.add(nodeSetList(dept));
		}
		return TreeUtil.bulid(treeList, "0");
	}

	@Override
	public List<DeptTree> asyncTreeList(SysDept sysDept) {
        CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
        Cache cache = cacheManager.getCache("tree_details");
        List<DeptTree> list = null;
        List<SysDept> depts = new ArrayList<>();
        String undefined= "undefined";
		if (sysDept.getName()==null || sysDept.getName()==""){sysDept.setName(undefined);}
		if (sysDept.getParentId()==null || sysDept.getParentId()==""){sysDept.setParentId(undefined);}
		if (sysDept.getCode()==null || sysDept.getCode()==""){sysDept.setCode(undefined);}
		if (sysDept.getArea()==null || sysDept.getArea()==""){sysDept.setArea(undefined);}
			String kye = (sysDept.getParentId()).concat("-").concat(sysDept.getCode()).concat("-").concat(sysDept.getName()).concat("-").concat(sysDept.getArea());
			Object obj = cache.get(kye);
			if(obj!=null){
				list = (List<DeptTree>) (((SimpleValueWrapper) obj).get());
			}
			if(CollectionUtils.isEmpty(list)){
            depts = baseMapper.asyncTreeList(sysDept);

            if (sysDept.getName()!=undefined){
				List<DeptTree> treeList = new ArrayList<>();
				for (SysDept dept : depts) {
                    treeList.add(nodeSetList(dept));
				}
				list = TreeUtil.bulid(treeList, "0");
			}else if (sysDept.getArea()!=undefined){
				list = getTreeList(depts);
			}else {
				List<DeptTree> treeList = new ArrayList<>();
				for (SysDept dept : depts) {
					treeList.add(nodeSetList(dept));
				}
				list = TreeUtil.buildByRecursive(treeList, sysDept.getParentId());
			}
				cache.put(kye, list);
			}
        return list;
    }

    public DeptTree nodeSetList(SysDept dept){
	    String deptId = "7B038CBFE0FIC09CE0530A282E21C09C";
        DeptTree node = new DeptTree();
        if (dept.getParentId().equals(deptId)){
            node.setOpen(true);
        }
        node.setId(dept.getDeptId());
        node.setSort(dept.getSort());
        node.setParentId(dept.getParentId());
        node.setName(dept.getName());
        node.setUpName(dept.getUpname());
        node.setCode(dept.getCode());
        node.setHasChildren(dept.getHasChildren());
	    return node;
    }

	/**
	 * 查询全部部门树
	 *
	 * @return 树
	 */
	@Override
	public List<DeptTree> selectTree() {
		return getDeptTree(baseMapper.deptList(new DataScope("code","sys_dept_list")));
	}

	/**
	 * 查询用户部门树
	 *
	 * @return
	 */
	@Override
	public List<DeptTree> getUserTree() {
		String deptId = SecurityUtils.getUser().getSysDept().getDeptId();
		List<String> descendantIdList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor, deptId))
				.stream().map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());

		List<SysDept> deptList = baseMapper.selectBatchIds(descendantIdList);
		return getDeptTree(deptList);
	}

	/**
	 * 构建部门树
	 *
	 * @param depts 部门
	 * @return
	 */
	private List<DeptTree> getDeptTree(List<SysDept> depts) {
		List<DeptTree> treeList = depts.stream()
				.filter(dept -> !dept.getDeptId().equals(dept.getParentId()))
				.map(dept -> {
					DeptTree node = new DeptTree();
					node.setId(dept.getDeptId());
					node.setSort(dept.getSort());
					node.setParentId(dept.getParentId());
					node.setName(dept.getName());
					node.setCode(dept.getCode());
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.bulid(treeList, "0");
	}
}
