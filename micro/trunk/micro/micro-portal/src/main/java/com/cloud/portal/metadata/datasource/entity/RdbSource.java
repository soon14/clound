package com.cloud.portal.metadata.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.portal.metadata.datasource.constant.DataSourceEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author vlong
 * @date Created in 2017/12/19 17:11
 * description: 关系型数据库基类
 * modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RdbSource extends DataSource {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String name;	//库名称
    private DataSourceEnum type;	//类型
    private String ip;	//IP地址
    private String account;	//账号
    private String pwd;	//密码
    private String port;	//端口
    private String serverName;	//实例名称
    private String schema;	//schema名
    private String del_flag; //删除标志
    private String dept;	//来源单位
    private String dbOwner;	//联系人
    private String phone;	//联系方式
    private String JdbcUrl; //数据库连接地址
    private String message; //连接结果信息
    private String create_by;
    /*private String area;	//所属行政区划*/
    private String link;  //0sid， 1service_name
    private String clusterFlag;  //是否集群
    private String clusterTns;  //集群tns信息

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSets;
}
