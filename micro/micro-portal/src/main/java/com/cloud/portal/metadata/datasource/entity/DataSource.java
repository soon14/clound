package com.cloud.portal.metadata.datasource.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.portal.metadata.datasource.constant.DataSourceEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author vlong
 * @date Created in 2017/12/19 16:57
 * description: 数据源基类
 * modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataSource extends Model<DataSource> {
    private String id;
    private String name;
    private DataSourceEnum type;
}
