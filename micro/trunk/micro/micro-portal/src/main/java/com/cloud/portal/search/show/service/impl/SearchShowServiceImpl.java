package com.cloud.portal.search.show.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.dto.RestParamDTO;
import com.cloud.admin.api.feign.RemoteApiService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.constant.SecurityConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.common.constant.JsonKey;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.common.util.DateUtil;
import com.cloud.portal.search.scheme.model.SearchScheme;
import com.cloud.portal.search.scheme.service.SearchSchemeService;
import com.cloud.portal.search.show.mapper.SearchShowMapper;
import com.cloud.portal.search.show.model.ColumnKey;
import com.cloud.portal.search.show.model.SearchEnum;
import com.cloud.portal.search.show.model.SearchKey;
import com.cloud.portal.search.show.model.SearchShow;
import com.cloud.portal.search.show.service.SearchShowService;
import com.google.common.base.CaseFormat;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wengshij
 * @date Created in 2019/8/5 14:36
 * @description:综合查询前端显示配置信息
 * @modified By:wengshij
 */
@Service
@AllArgsConstructor
public class SearchShowServiceImpl extends ServiceImpl<SearchShowMapper, SearchShow> implements SearchShowService {

    private final SearchSchemeService searchSchemeService;

    private final RemoteApiService remoteApiService;
    /**
     * 缓存信息
     */
    private final CacheManager cacheManager;

    private final String KEY_NAME = "json_";

    @Override
    public IPage<Map> listPage(Page page, SearchShow searchShow) {

        SearchShow tempSearchShow = initSearchContent(searchShow);
        if (null == tempSearchShow) {
            return null;
        }
        if (PortalConstants.SEARCH_TYPE_REST_API.equals(tempSearchShow.getSearchType())) {
            return apiRestRequest(page, tempSearchShow);
        } else {
            return changeDataType(this.baseMapper.listPage(page, tempSearchShow));
        }
    }

    /**
     * 请求第三方rest 接口信息
     *
     * @param page       分页信息
     * @param searchShow 查询信息
     * @return
     */
    private IPage<Map> apiRestRequest(Page page, SearchShow searchShow) {
        Map<String, Object> map = new HashMap(2);
        map.put("orderByKey", searchShow.getOrderByKey());
        Map<String, String> searchMap = new HashMap<>(16);
        List<SearchKey> searchKeyList = searchShow.getSearchKeyList();
        if (null != searchKeyList && searchKeyList.size() > 0) {
            searchKeyList.forEach(searchKey -> {
                String symbol = searchKey.getSymbol();
                symbol=StringUtils.isBlank(symbol) ? SearchEnum.EQ.value() : symbol.toUpperCase();
                String column = searchKey.getColumn();
                Object value = searchKey.getValue();
                SearchEnum searchEnum = SearchEnum.getByValue(symbol);
                switch (searchEnum) {
                    case GT:
                        searchMap.put(column, ">#" + value.toString());
                        break;
                    case IN:
                        if (value.getClass().isArray() == true) {
                            searchMap.put(column, "in#" + StringUtils.join((Object[]) value, ","));
                        } else {
                            searchMap.put(column, "in#" + value.toString());
                        }
                        break;
                    case LT:
                        searchMap.put(column, "<#" + value.toString());
                        break;
                    case GT_EQ:
                        searchMap.put(column, ">=#" + value.toString());
                        break;
                    case LT_EQ:
                        searchMap.put(column, "<=#" + value.toString());
                        break;
                    case NOT_IN:
                        if (value.getClass().isArray()) {
                            searchMap.put(column, "not in#" + StringUtils.join((Object[]) value, ","));
                        } else {
                            searchMap.put(column, "not in#" + value.toString());
                        }
                        break;
                    case ALL_LIKE:
                        searchMap.put(column, "like#" + value.toString());
                        break;
                    case LEFT_LIKE:
                        searchMap.put(column, "like#%" + value.toString());
                        break;
                    case RIGHT_LIKE:
                        searchMap.put(column, "like#" + value.toString() + "%");
                        break;
                    case BETWEEN:
                        if (value.getClass().isArray()) {
                            Object[] objects = (Object[]) value;
                            if (null != objects && objects.length == 2) {
                                searchMap.put(column, "between#" + objects[0].toString() + "," + objects[1].toString());
                            }
                        } else {
                            searchMap.put(column, "between#" + value.toString() + "," + value.toString());
                        }
                        break;
                    case EQ:
                    default:
                        searchMap.put(column, value.toString());
                }
            });
        }
        map.put("searchKey", searchMap);
        RestParamDTO restParamDTO = RestParamDTO.initRest(searchShow.getApiUrl(),
                searchShow.getApiUserName(),
                searchShow.getApiPwd(),
                searchShow.getMethod(),
                searchShow.getTokenUrl(),
                "1",
                map,
                page);
        R<Page> tempPage = remoteApiService.restPage(restParamDTO);
        if (CommonConstants.SUCCESS == tempPage.getCode()) {
            return tempPage.getData();
        } else {
            throw new RuntimeException(tempPage.getMsg());
        }

    }

    @Override
    public SearchShow initSearchContent(SearchShow searchShow) {
        return initContent(searchShow);
    }

    private SearchShow initContent(SearchShow entity) {
        SearchShow searchShow = new SearchShow();
        searchShow.setSearchMap(entity.getSearchMap());
        if (StringUtils.isNotBlank(entity.getSchemeId())) {
            Cache cache = cacheManager.getCache(PortalConstants.SEARCH_SCHEME_CACHE_KEY);
            Object object = null != cache ? cache.get(KEY_NAME + entity.getSchemeId()) : null;
            if (null != object) {
                searchShow = (SearchShow) ((SimpleValueWrapper) object).get();
                searchShow.setSearchMap(entity.getSearchMap());
                searchShow.setSearchKeyList(resetSearchKey(entity.getSearchMap(), searchShow.getColumns()));
                return searchShow;
            }
            SearchScheme searchScheme = searchSchemeService.getById(entity.getSchemeId());
            boolean isVerity = (null == searchScheme
                    || StringUtils.isBlank(searchScheme.getSearchContent())
                    || StringUtils.isBlank(searchScheme.getSearchInput())
            );
            if (isVerity) {
                return null;
            }
            // 0 表示业务表 1表示接口信息
            String searchType = searchScheme.getSearchType();
            searchShow.setSearchType(searchType);
            if (PortalConstants.SEARCH_TYPE_TABLE.equals(searchType)) {
                searchShow.setTableName(searchScheme.getSearchInput());
            } else if (PortalConstants.SEARCH_TYPE_REST_API.equals(searchType)) {
                searchShow.setMethod(searchScheme.getRequestType());
                searchShow.setTokenUrl(searchScheme.getTokenAddress());
                searchShow.setApiUrl(searchScheme.getSearchInput());
                searchShow.setApiPwd(searchScheme.getApiPwd());
                searchShow.setApiUserName(searchScheme.getApiUserName());
            }
            String searchContent = searchScheme.getSearchContent();
            List<ColumnKey> columnKeyList = new ArrayList<>();
            JSONObject jsonSearchContent = JSONUtil.parseObj(searchContent);
            JSONArray jsonArray = jsonSearchContent.getJSONArray(JsonKey.COLUMN);
            if (CollectionUtil.isEmpty(jsonArray)) {
                return null;
            }
            String dataScope = jsonSearchContent.getStr(JsonKey.DATA_SCOPE);
            if (StringUtils.isNotBlank(dataScope)) {
                searchShow.setDataScope(DataScopeUtil.getDataScopeSql(dataScope, null));
            }
            Map<String, String> orderByKey = new HashMap<>(16);
            jsonArray.stream().forEach(jsonObject -> {
                if (jsonObject instanceof JSONObject) {
                    JSONObject tempJson = (JSONObject) jsonObject;
                    String objProp = tempJson.getStr(JsonKey.COLUMN_PROP);
                    //英文字段名称
                    if (StringUtils.isNotBlank(objProp)) {
                        ColumnKey columnKey = new ColumnKey();
                        String column = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, objProp);
                        columnKey.setRealName(objProp);
                        columnKey.setShamName(column);
                        //中文字段名称
                        String label = tempJson.getStr(JsonKey.COLUMN_LABEL);
                        if (StringUtils.isNotBlank(label)) {
                            columnKey.setCnName(label);
                        }
                        //排序字段
                        String orderBy = tempJson.getStr(JsonKey.COLUMN_ORDER_BY);
                        if (StringUtils.isNotBlank(orderBy)) {
                            orderByKey.put(columnKey.getShamName(), JsonKey.SORT_ASC.equalsIgnoreCase(orderBy) ? JsonKey.SORT_ASC : JsonKey.SORT_DESC);
                            columnKey.setOrderBy(orderBy.toLowerCase());
                        }
                        //查询字段
                        Boolean search = tempJson.getBool(JsonKey.COLUMN_SEARCH);
                        if (null != search && search) {
                            //查询标识符 like left like rightLike eq
                            String symbol = tempJson.getStr(JsonKey.COLUMN_SYMBOL);
                            if (StringUtils.isNotBlank(symbol)) {
                                columnKey.setSymbol(symbol.toLowerCase());
                            }
                        }
                        //字段类型
                        String type = tempJson.getStr(JsonKey.COLUMN_LTYPE);
                        if (StringUtils.isNotBlank(type)) {
                            columnKey.setType(type.toLowerCase());
                        }
                        columnKeyList.add(columnKey);
                    }
                }
            });
            searchShow.setColumns(columnKeyList);
            searchShow.setOrderByKey(orderByKey);
            searchShow.setSearchKeyList(resetSearchKey(searchShow.getSearchMap(), columnKeyList));
            if (null != searchShow) {
                cacheManager.getCache(PortalConstants.SEARCH_SCHEME_CACHE_KEY).put(KEY_NAME + entity.getSchemeId(), searchShow);
            }
            return searchShow;
        }
        return null;
    }


    /**
     * 根据类型设置查询参数
     */
    private SearchKey setSearchColumn(ColumnKey columnKey, Object value) {
        SearchKey searchKey = new SearchKey();
        String regYear = "[0-9]{4}";
        String reg = "[0-9]{4}-[0-9]{2}";
        String symbolGt = "gt";
        String symbolLt = "lt";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isBlank(columnKey.getSymbol()) && value.toString().matches(regYear)) {
            String startYearTime = value.toString().concat("-01-01").concat(" 00:00:00");
            String endYearTime = value.toString().concat("-12-31").concat(" 23:59:59");
            searchKey.setStartTime(startYearTime);
            searchKey.setEndTime(endYearTime);
        } else if (StringUtils.isBlank(columnKey.getSymbol()) && value.toString().matches(reg)) {
            Date d = DateUtil.parseDate(value);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            searchKey.setStartTime(value.toString().concat("-01 00:00:00"));
            searchKey.setEndTime(value.toString().concat("-" + lastDay).concat(" 23:59:59"));
        } else {
            switch (columnKey.getType().toLowerCase()) {
                case "date":
                case "datetime":
                    Date d = (value.toString().matches(regYear) == true ? null : DateUtil.parseDate(value));
                    String str = (d == null ? null : formatter.format(d));
                    if (StringUtils.isNotBlank(columnKey.getSymbol()) && (columnKey.getSymbol().equals(symbolGt) || columnKey.getSymbol().equals(symbolLt))) {
                        searchKey.setSymbol(columnKey.getSymbol());
                        if (StringUtils.isNotBlank(str)) {
                            searchKey.setSearchDate(str);
                        } else {
                            String startYearTime = value.toString().concat("-01-01 00:00:00");
                            String endYearTime = value.toString().concat("-12-31 23:59:59");
                            searchKey.setSearchDate(columnKey.getSymbol().equals(symbolGt) == true ? endYearTime : startYearTime);
                        }
                    } else {
                        searchKey.setStartTime(str);
                        searchKey.setEndTime(formatDay.format(d).concat(" 23:59:59"));
                    }
                    break;
                case "daterange":
                case "datetimerange":
                    Object[] obj = (Object[]) value;
                    Date startDate = DateUtil.parseDate(obj[0]);
                    Date endDate = DateUtil.parseDate(obj[1]);
                    if (obj[0].toString().matches(regYear) && obj[1].toString().matches(regYear)) {
                        searchKey.setStartTime(obj[0].toString().concat("-01-01").concat(" 00:00:00"));
                        searchKey.setEndTime(obj[1].toString().concat("-12-31").concat(" 23:59:59"));
                    } else if (obj[0].toString().matches(reg) && obj[1].toString().matches(reg)) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(endDate);
                        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                        searchKey.setStartTime(obj[0].toString().concat("-01").concat(" 00:00:00"));
                        searchKey.setEndTime(obj[1].toString().concat("-" + lastDay).concat(" 23:59:59"));
                    } else {
                        searchKey.setStartTime(formatter.format(startDate));
                        searchKey.setEndTime(formatter.format(endDate));
                    }
                    break;
                default:
                    searchKey.setSymbol(value.getClass().isArray() == true ? "in" : columnKey.getSymbol());
                    searchKey.setValue(value);
                    break;
            }
        }
        searchKey.setColumn(columnKey.getShamName());
        return searchKey;
    }

    private IPage<Map> changeDataType(IPage<Map> iPage) {
        if (null != iPage && null != iPage.getRecords() && iPage.getRecords().size() > 0) {
            List<Map> listMap = iPage.getRecords();
            for (int i = 0, j = listMap.size(); i < j; i++) {
                Set<Map.Entry<String, Object>> entrySet = listMap.get(i).entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    if (null != entry.getValue() && entry.getValue() instanceof Clob) {
                        entry.setValue(getClob((Clob) entry.getValue()));
                    }
                }
            }
        }
        return iPage;
    }

    private String getClob(Clob clob) {
        String reString;
        java.io.Reader is = null;
        try {
            is = clob.getCharacterStream();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(is);
        String str;
        StringBuffer sb = new StringBuffer();
        try {
            str = br.readLine();
            while (str != null) {
                sb.append(str);
                str = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reString = sb.toString();
        return reString;
    }

    /**
     * 查询条件进一步封装
     *
     * @param searchMap
     * @param columnKeyList
     * @return
     */
    private List<SearchKey> resetSearchKey(Map<String, Object> searchMap, List<ColumnKey> columnKeyList) {
        List<SearchKey> searchKeyList = new ArrayList<>();
        //查询条件进一步封装
        if (CollectionUtil.isNotEmpty(searchMap)) {
            searchMap.forEach((key, value) -> {
                columnKeyList.stream().forEach(columnKey -> {
                    boolean flag = (null != value && StringUtils.isNotBlank(key) && key.equalsIgnoreCase(columnKey.getRealName()));
                    if (flag && StringUtils.isNotBlank(value.toString())) {
                        SearchKey searchKey = new SearchKey();
                        if (StringUtils.isNotBlank(columnKey.getType())) {
                            searchKey = setSearchColumn(columnKey, value);
                        } else {
                            searchKey.setColumn(columnKey.getShamName());
                            searchKey.setSymbol(columnKey.getSymbol());
                            searchKey.setValue(value);
                        }
                        searchKeyList.add(searchKey);
                    }
                });
            });
        }
        return searchKeyList;
    }

}
