package com.cloud.portal.search.show.model;

/**
 * @author wengshij
 * @date Created in 2019/8/14 16:15
 * @description:查询条件类型
 * @modified By:wengshij
 */
public enum SearchEnum {
    /**
     * 全匹配模糊查询
     */
    ALL_LIKE("LIKE", "LIKE"),
    /**
     * 左匹配模糊查询
     */
    LEFT_LIKE("LEFTLIKE", "LEFTLIKE"),
    /**
     * 右匹配模糊查询
     */
    RIGHT_LIKE("RIGHTLIKE", "RIGHTLIKE"),
    /**
     * 集合查询
     */
    IN("IN", "IN"),
    /**
     * 集合查询
     */
    NOT_IN("NOTIN", "NOTIN"),
    /**
     * 大于号查询
     */
    GT("GT", "GT"),
    /**
     * 小于号查询
     */
    LT("LT", "LT"),
    /**
     * 大于等于
     */
    GT_EQ("GTEQ", "GTEQ"),
    /**
     * 小于等于
     */
    LT_EQ("LTEQ", "LTEQ"),
    /**
     * 范围查询
     */
    BETWEEN("BETWEEN", "BETWEEN"),
    /**
     * 相等
     */
    EQ("EQ", "EQ");


    /**
     * code值
     */
    private String code;
    /**
     * value值
     */
    private String value;

    SearchEnum(String code, String value) {
        this.code = code;
        this.value = value;

    }

    public static SearchEnum getByValue(String value) {
        for (SearchEnum searchEnum : values()) {
            if (searchEnum.value.equalsIgnoreCase(value)) {
                return searchEnum;
            }
        }
        return SearchEnum.EQ;
    }

    public String code() {
        return this.code;
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return "SearchEnum{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
