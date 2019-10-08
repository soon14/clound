package com.cloud.portal.metadata.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.standardfields.entity.Table;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
@Mapper
public interface RdbSourceMapper extends BaseMapper<RdbSource>{

    /**
     * 数据源 列表
     * @param page
     * @param rdbSource
     * @return
     */
    IPage<RdbSource> findOraclePage(IPage<RdbSource> page,@Param("queryCondition")RdbSource rdbSource);
    IPage<RdbSource> findMySqlPage(IPage<RdbSource> page,@Param("queryCondition")RdbSource rdbSource);
    IPage<RdbSource> findSQLServerPage(IPage<RdbSource> page,@Param("queryCondition")RdbSource rdbSource);
    IPage<RdbSource> findDB2Page(IPage<RdbSource> page,@Param("queryCondition")RdbSource rdbSource);

    /**
     * 数据源重名检测
     * @param rdbSource
     * @return
     */
    RdbSource getOracleName(RdbSource rdbSource);
    RdbSource getMySqlName(RdbSource rdbSource);
    RdbSource getSQLServerName(RdbSource rdbSource);
    RdbSource getDB2Name(RdbSource rdbSource);

    /**
     * 删除数据源
     * @param id
     * @return
     */
    boolean removesourceOracleId(String id);
    boolean removesourceMySqlId(String id);
    boolean removesourceSQLServerId(String id);
    boolean removesourceDB2Id(String id);

    /**
     * 数据源 添加
     * @param rdbSource
     * @return
     */
    boolean savedatasourceMySql(RdbSource rdbSource);

    boolean savedatasourceOracle(RdbSource rdbSource);

    boolean savedatasourceDB2(RdbSource rdbSource);

    boolean savedatasourceSQLServer(RdbSource rdbSource);

    /**
     * 数据源 更新
     * @param rdbSource
     * @return
     */
    boolean updatesourceOracleById(RdbSource rdbSource);
    boolean updatesourceMySqlById(RdbSource rdbSource);
    boolean updatesourceSQLServerById(RdbSource rdbSource);
    boolean updatesourceDB2ById(RdbSource rdbSource);

    /**
     * 获取所有数据库byID
     * @param table
     */
    RdbSource getById(@Param("queryCondition")Table table);
    /**
     * 获取oracle数据库信息byID
     * @param table
     */
    RdbSource getOracleSource(@Param("queryCondition")Table table);
    /**
     * 获取db2
     * @param table
     */
    RdbSource getDb2Source(@Param("queryCondition")Table table);
}
