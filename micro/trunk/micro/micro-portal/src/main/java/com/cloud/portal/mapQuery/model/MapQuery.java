package com.cloud.portal.mapQuery.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/8/289:53
 * @description:
 * @modified By:lvlinc
 */
@Data
public class MapQuery extends Model<MapQuery> {
    /**场所id**/
    private String placeId;
    /**场所名称**/
    private String placeName;
    /**场所类型**/
    private String placeType;
    @JsonIgnore
    private List<String> placeTypeList;
    /**等级代码**/
    private String levelType;
    @JsonIgnore
    private List<String> levelTypeList;
    /**行政区划**/
    private String cityCode;
    /**来源系统**/
    private String sourceType;
    /**场所地址**/
    private String placeAddress;

    private int rowNum;
}
