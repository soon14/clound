package com.cloud.portal.metadata.datasource.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.standardfields.entity.Table;

public interface RdbSourceService extends IService<RdbSource> {

    /**
     * 添加数据源
     * @param rdbSource
     * @return
     */
    boolean savedatasourceType(RdbSource rdbSource);

    /**
     * 更新数据源
     * @param rdbSource
     * @return
     */
    boolean updatesourceById(RdbSource rdbSource);

    /**
     * 查询数据源表
     * @param rdbSource
     * @return
     */
    IPage<RdbSource> findFieldPage(IPage<RdbSource> page,RdbSource rdbSource);

    /**
     * 检测数据库是否重复
     * @param rdbSource
     * @return
     */
    RdbSource getOneByName(RdbSource rdbSource);
    /**
     * 获取所有数据库byID
     * @param table
     */
    RdbSource getById(Table table);
    /**
     * 获取oracle数据库信息byID
     * @param table
     */
    RdbSource getOracleSource(Table table);
    /**
     * 获取db2
     * @param table
     */
    RdbSource getDb2Source(Table table);

    /**
     * 删除数据源
     * @param id,type
     * @return
     */
    boolean removesourceById(String id,String type);
}
