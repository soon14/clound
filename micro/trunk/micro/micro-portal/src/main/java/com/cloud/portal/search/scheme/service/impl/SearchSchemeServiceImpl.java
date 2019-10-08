package com.cloud.portal.search.scheme.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cloud.admin.api.dto.DictTree;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.JsonKey;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.common.feign.SysDictApiUtil;
import com.cloud.portal.search.scheme.mapper.SearchSchemeMapper;
import com.cloud.portal.search.scheme.model.SearchScheme;
import com.cloud.portal.search.scheme.model.SearchSchemeTree;
import com.cloud.portal.search.scheme.service.SearchSchemeService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ryt
 * @date Created in 2019/8/5 11:21
 * @description: 综合查询-查询方案配置
 * @modified By:
 */
@Service
@AllArgsConstructor
public class SearchSchemeServiceImpl extends ServiceImpl<SearchSchemeMapper, SearchScheme> implements SearchSchemeService {

    private final CacheManager cacheManager;

    /**
     * 新增查询方案配置
     *
     * @param searchScheme
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PortalConstants.SEARCH_SCHEME_CACHE_KEY, allEntries = true)
    public R saveScheme(SearchScheme searchScheme) {
        boolean result = validJson(searchScheme.getSearchContent());
        if (!result) {
            return R.failed("JSON格式错误");
        }
        MicroUser user = SecurityUtils.getUser();
        searchScheme.setCreateBy(user.getId());
        searchScheme.setUpdateBy(searchScheme.getCreateBy());
        searchScheme.setCreateTime(new Date());
        searchScheme.setUpdateTime(searchScheme.getCreateTime());
        searchScheme.setDelFlag(CommonConstants.STATUS_NORMAL);
        return R.ok(baseMapper.insert(searchScheme));
    }

    /**
     * 查询方案配置修改
     *
     * @param searchScheme
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PortalConstants.SEARCH_SCHEME_CACHE_KEY, allEntries = true)
    public R updateScheme(SearchScheme searchScheme) {
        boolean result = validJson(searchScheme.getSearchContent());
        if (!result) {
            return R.failed("JSON格式错误");
        }
        MicroUser user = SecurityUtils.getUser();
        searchScheme.setUpdateBy(user.getId());
        searchScheme.setUpdateTime(new Date());
        return R.ok(baseMapper.updateById(searchScheme));
    }

    /**
     * json格式校验
     *
     * @param json
     * @return
     */
    public boolean validJson(String json) {
        boolean result = false;
        try {
            json = json.trim();
            if (json.startsWith("{") && json.endsWith("}")) {
                JSONObject searchContent = JSONUtil.parseObj(json);
                JSONArray jsonArray = searchContent.getJSONArray(JsonKey.COLUMN);
                if (null != jsonArray && jsonArray.size() > 0) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询方案配置删除
     *
     * @param id 方案id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PortalConstants.SEARCH_SCHEME_CACHE_KEY, allEntries = true)
    public R removeById(String id) {
        return R.ok(SqlHelper.delBool(baseMapper.deleteById(id)));
    }

    /**
     * 根据主键ID 更新配置方案的状态、（启用或者停用）
     *
     * @param id     方案id
     * @param status 方案状态
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PortalConstants.SEARCH_SCHEME_CACHE_KEY, allEntries = true)
    public R updateStatus(String id, String status) {
        //是否参数缺失
        boolean loseParams = StringUtils.isBlank(id) || StringUtils.isBlank(status) || (!status.equals("0") && !status.equals("1"));
        if (loseParams) {
            return R.failed("参数丢失、操作失败!");
        }
        MicroUser user = SecurityUtils.getUser();
        SearchScheme searchScheme = new SearchScheme();
        searchScheme.setId(id);
        //替换参数、因为前端传过来是默认的状态、需要启用或者停用、需要交换当前状态
        searchScheme.setStatus(status.equals("0") ? "1" : "0");
        searchScheme.setUpdateBy(user.getId());
        searchScheme.setUpdateTime(new Date());
        return R.ok(SqlHelper.delBool(baseMapper.updateStatus(searchScheme)));
    }

    /**
     * 获取查询配置方案树结构
     *
     * @param
     * @return
     */
    @Override
    public List<SearchSchemeTree> getSchemeTree() {
        Cache cache = cacheManager.getCache(PortalConstants.SEARCH_SCHEME_CACHE_KEY);
        Object object = null != cache ? cache.get("search_scheme_tree") : null;
        if (null != object) {
            return (List<SearchSchemeTree>) ((SimpleValueWrapper) object).get();
        }
        SearchScheme searchScheme = new SearchScheme();
        searchScheme.setStatus("0");
        List<SearchScheme> searchSchemeList = baseMapper.selectList(Wrappers.query(searchScheme)).stream()
                .sorted(Comparator.comparing(SearchScheme::getSorts))
                .collect(Collectors.toList());

        List<SysDict> sysDictList = SysDictApiUtil.getDictList("search_scheme_classification").stream()
                .sorted(Comparator.comparingInt(SysDict::getSort))
                .collect(Collectors.toList());

        List<SearchSchemeTree> searchSchemeTreeList = new ArrayList<>();

        for (int i = 0; i < sysDictList.size(); i++) {
            SearchSchemeTree schemeTree = new SearchSchemeTree();
            schemeTree.setId(sysDictList.get(i).getId());
            schemeTree.setParentId(sysDictList.get(i).getParentId());
            schemeTree.setName(sysDictList.get(i).getLabel());
            schemeTree.setClassification(sysDictList.get(i).getValue());
            schemeTree.setRemark(sysDictList.get(i).getRemarks());
            schemeTree.setScheme(Boolean.FALSE);
            searchSchemeTreeList.add(schemeTree);

            if (searchSchemeList.size() > 0) {
                for (int j = 0; j < searchSchemeList.size(); j++) {
                    if (schemeTree.getClassification().equals(searchSchemeList.get(j).getClassification())) {
                        SearchSchemeTree schemeTree1 = new SearchSchemeTree();
                        schemeTree1.setId(searchSchemeList.get(j).getId());
                        schemeTree1.setParentId(schemeTree.getId());
                        schemeTree1.setName(searchSchemeList.get(j).getName());
                        schemeTree1.setClassification(searchSchemeList.get(j).getClassification());
                        schemeTree1.setSearchContent(searchSchemeList.get(j).getSearchContent());
                        schemeTree1.setSearchInput(searchSchemeList.get(j).getSearchInput());
                        schemeTree1.setApiUserName(searchSchemeList.get(j).getApiUserName());
                        schemeTree1.setApiPwd(searchSchemeList.get(j).getApiPwd());
                        schemeTree1.setSearchType(searchSchemeList.get(j).getSearchType());
                        schemeTree1.setSorts(searchSchemeList.get(j).getSorts());
                        schemeTree1.setRemark(searchSchemeList.get(j).getRemark());
                        schemeTree1.setTokenAddress(searchSchemeList.get(j).getTokenAddress());
                        schemeTree1.setRequestType(searchSchemeList.get(j).getRequestType());
                        schemeTree1.setScheme(Boolean.TRUE);
                        searchSchemeTreeList.add(schemeTree1);
                    }
                }
            }
        }
        searchSchemeTreeList = TreeUtil.buildByRecursive(searchSchemeTreeList, "0");
        cacheManager.getCache(PortalConstants.SEARCH_SCHEME_CACHE_KEY).put("search_scheme_tree", searchSchemeTreeList);
        return searchSchemeTreeList;
    }

}
