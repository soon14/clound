package com.cloud.portal.judged.lib.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.dto.DeptTree;
import com.cloud.admin.api.dto.DictTree;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.feign.SysDictApiUtil;
import com.cloud.portal.judged.lib.mapper.PlanLibraryMapper;
import com.cloud.portal.judged.lib.model.PlanLibrary;
import com.cloud.portal.judged.lib.service.PlanLibraryService;
import com.cloud.portal.metadata.standardfields.entity.Table;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlanLibraryImpl extends ServiceImpl<PlanLibraryMapper, PlanLibrary> implements PlanLibraryService {

    @Override
    public IPage<List<PlanLibrary>> findPage(IPage<PlanLibrary> iPage, PlanLibrary planLibrary) {
        String dataSql = DataScopeUtil.getDataScopeSql(new DataScope("creat_by","",""));
        planLibrary.setDataScope(dataSql);
        return this.baseMapper.findPage(iPage,planLibrary);
    }

    @Override
    public boolean insertPlanLibrary(PlanLibrary planLibrary) {
        planLibrary.setDelFlag("0");
        Date date = new Date();
        planLibrary.setCreateTime(date);
        MicroUser user = SecurityUtils.getUser();
        planLibrary.setCreateBy(user.getId());
        planLibrary.setDeptCode(user.getSysDept().getCode());
        return baseMapper.insertPlanLibrary(planLibrary);
    }

    @Override
    public List<DeptTree> fetchLibrarylist(PlanLibrary planLibrary) {
        CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
        Cache cache = cacheManager.getCache("tree_details");
        List<DeptTree> list = null;

        List<PlanLibrary> planLibraries = new ArrayList<>();
        String undefined = "undefined";
        if (planLibrary.getUseBy() == null || planLibrary.getUseBy() == "") {
            planLibrary.setUseBy("0");
        }

        String kye = ("planLibrary-HYLX".concat("-").concat(planLibrary.getUseBy()));
        Object obj = cache.get(kye);
//        if (obj != null) {
//            list = (List<DeptTree>) (((SimpleValueWrapper) obj).get());
//        }
        if (CollectionUtils.isEmpty(list)) {
            List<DeptTree> treeList = new ArrayList<>();
            List<SysDict> sysDicts = SysDictApiUtil.getDictList("HYLX");
            List<DictTree> sysDictAndPlanLibrary = SysDictApiUtil.getDictTreeList(sysDicts);
            for (int i = 0; i < sysDictAndPlanLibrary.size(); i++) {
                PlanLibrary planLibraryGetByType = new PlanLibrary();
                planLibraryGetByType.setPlaceType(sysDictAndPlanLibrary.get(i).getValue());
                List<PlanLibrary> planLibraryList = baseMapper.selectByType(planLibraryGetByType);
                DeptTree node = new DeptTree();
                node.setId(sysDictAndPlanLibrary.get(i).getId());
                node.setSort(sysDictAndPlanLibrary.get(i).getSort());
                node.setParentId(sysDictAndPlanLibrary.get(i).getParentId());
                node.setName(sysDictAndPlanLibrary.get(i).getLabel());
//                node.setUpName(dept.getUpname());
                node.setCode(sysDictAndPlanLibrary.get(i).getValue());
//                if (planLibraryList.size()>0){
                    node.setHasChildren("true");
//                }
                treeList.add(node);
                if(planLibraryList.size()>0){
                    for(int j=0;j<planLibraryList.size();j++){
                        DeptTree nodePl = new DeptTree();
                        nodePl.setId(planLibraryList.get(j).getId());
                        nodePl.setSort(node.getSort());
                        nodePl.setParentId(node.getId());
                        nodePl.setName(planLibraryList.get(j).getName());
//                node.setUpName(dept.getUpname());
                        nodePl.setCode(planLibraryList.get(j).getId());
                        treeList.add(nodePl);
                    }
                }
            }
                list = treeList;
//            }else if (sysDept.getArea()!=undefined){
//                list = getTreeList(depts);
//            }else {
//                List<DeptTree> treeList = new ArrayList<>();
//                for (SysDept dept : depts) {
//                    DeptTree node = new DeptTree();
//                    node.setId(dept.getDeptId());
//                    node.setSort(dept.getSort());
//                    node.setParentId(dept.getParentId());
//                    node.setName(dept.getName());
//                    node.setUpName(dept.getUpname());
//                    node.setCode(dept.getCode());
//                    treeList.add(node);
//                }
//                list = TreeUtil.buildByRecursive(treeList, sysDept.getParentId());
//            }
            cache.put(kye, list);
//        }
//        return list;
        }
        list = TreeUtil.buildByRecursive(list, "0");
        return list;
    }


}
