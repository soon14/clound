package com.cloud.portal.search.show.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/8/7 17:46
 * @description:
 * @modified By:wengshij
 */
@Data
public class SearchKey implements Serializable {
    private String column;
    private Object value;
    private String symbol;
    /**开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;
    private String searchDate;
}
