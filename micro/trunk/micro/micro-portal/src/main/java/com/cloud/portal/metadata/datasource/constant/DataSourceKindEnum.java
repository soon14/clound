package com.cloud.portal.metadata.datasource.constant;

/**
 * @author vlong
 * @date Created in 2017/12/21 10:48
 * description: 数据源种类，是数据源类型的分类
 * modified By:
 */
public enum DataSourceKindEnum {
    FILE("file","文本类型"), RDB("rdb","关系型数据库"),
    NoSQL("noSql","noSql"),STREAM("stream","数据流"),
    OTHER("other","其他");
    public String value;
    public String name;
    DataSourceKindEnum(String value, String name){
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.value;
    }


}
