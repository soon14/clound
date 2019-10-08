package com.cloud.portal.metadata.datasource.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.core.util.R;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.datasource.mapper.RdbSourceMapper;
import com.cloud.portal.metadata.datasource.service.RdbSourceService;
import com.cloud.portal.metadata.standardfields.entity.Table;
import org.springframework.stereotype.Service;

@Service
public class RdbSourceImpl extends ServiceImpl<RdbSourceMapper,RdbSource> implements RdbSourceService {

    /**
     * 数据源列表
     * @param rdbSource
     * @return
     */
    @Override
    public IPage<RdbSource> findFieldPage(IPage<RdbSource> page, RdbSource rdbSource) {
        IPage<RdbSource>  Page = null;
        if (rdbSource.getType().name()!=" " && rdbSource.getType().name()!=null) {
            if (rdbSource.getType().name().equals("Oracle")) {
                Page = baseMapper.findOraclePage(page, rdbSource);
            } else if (rdbSource.getType().name().equals("MySql")) {
                Page = baseMapper.findMySqlPage(page, rdbSource);
            } else if (rdbSource.getType().name().equals("SQLServer")) {
                Page = baseMapper.findSQLServerPage(page, rdbSource);
            } else if (rdbSource.getType().name().equals("DB2")) {
                Page = baseMapper.findDB2Page(page, rdbSource);
            }
        }
        return Page;
    }

    /**
     * 添加数据源
     * @param rdbSource
     * @return
     */
    @Override
    public boolean savedatasourceType(RdbSource rdbSource) {
        rdbSource.setDel_flag("0");
        boolean list= true;
        if (rdbSource.getType().name()!=" " && rdbSource.getType().name()!=null){
        if(rdbSource.getType().name().equals("Oracle")){
            list = baseMapper.savedatasourceOracle(rdbSource);
        }else if (rdbSource.getType().name().equals("MySql") ){
            list = baseMapper.savedatasourceMySql(rdbSource);
        }else if (rdbSource.getType().name().equals("SQLServer") ){
            list = baseMapper.savedatasourceSQLServer(rdbSource);
        }else if (rdbSource.getType().name().equals("DB2") ){
            list = baseMapper.savedatasourceDB2(rdbSource);
        }}

        return list;
    }

    /**
     * 更新数据源
     * @param rdbSource
     * @return
     */
    @Override
    public boolean updatesourceById(RdbSource rdbSource) {
        rdbSource.setDel_flag("0");
        boolean list= true;
        if (rdbSource.getType().name()!=" " && rdbSource.getType().name()!=null){
        if(rdbSource.getType().name().equals("Oracle")){
            list = baseMapper.updatesourceOracleById(rdbSource);
        }else if (rdbSource.getType().name().equals("MySql") ){
            list = baseMapper.updatesourceMySqlById(rdbSource);
        }else if (rdbSource.getType().name().equals("SQLServer") ){
            list = baseMapper.updatesourceSQLServerById(rdbSource);
        }else if (rdbSource.getType().name().equals("DB2") ){
            list = baseMapper.updatesourceDB2ById(rdbSource);
        }}

        return list;
    }

    /**
     * 数据源重名检测
     * @param rdbSource
     * @return
     */
    @Override
    public RdbSource getOneByName(RdbSource rdbSource) {
        rdbSource.setDel_flag("0");
        return baseMapper.getOracleName(rdbSource);
    }

    /**
     * 删除数据源
     * @param id
     * @return
     */
    @Override
    public boolean removesourceById(String id,String type) {
        boolean list= true;
       
            if(type.equals("Oracle")){
                list = baseMapper.removesourceOracleId(id);
            }else if (type.equals("MySql") ){
                list = baseMapper.removesourceMySqlId(id);
            }else if (type.equals("SQLServer") ){
                list = baseMapper.removesourceSQLServerId(id);
            }else if (type.equals("DB2") ){
                list = baseMapper.removesourceDB2Id(id);
            }

        return list;
    }

    /**
     * 获取所有数据库byID
     * @param table
     */
    @Override
    public RdbSource getById(Table table){
        return  baseMapper.getById(table);
    }
    /**
     * 获取oracle数据库信息byID
     * @param table
     */
    @Override
    public RdbSource getOracleSource(Table table){
        return  baseMapper.getOracleSource(table);
    }
    /**
     * 获取db2
     * @param table
     */
    @Override
    public RdbSource getDb2Source(Table table){
        return  baseMapper.getDb2Source(table);
    }

}
