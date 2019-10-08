

package com.cloud.portal.metadata.standardfields.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.feign.RemoteDictService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.metadata.datasource.Util.TableUtil;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.datasource.service.RdbSourceService;
import com.cloud.portal.metadata.standardfields.constant.TableKindEnum;
import com.cloud.portal.metadata.standardfields.entity.Field;
import com.cloud.portal.metadata.standardfields.entity.Table;
import com.cloud.portal.metadata.standardfields.mapper.FieldMapper;
import com.cloud.portal.metadata.standardfields.mapper.TableMapper;
import com.cloud.portal.metadata.standardfields.service.FieldService;
import com.cloud.portal.metadata.standardfields.service.TableService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表
 * @modified By:
 */
@Service
@AllArgsConstructor
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements TableService {
    private final RemoteDictService dictService;
    @Autowired
    private FieldMapper fieldMapper;
    private RdbSourceService rdbSourceService;
    private FieldService fieldService;

    /**
     * 查询全部部门树
     *
     * @return 树
     */
    @Override
    public List<Map<String, Object>> selectTree() {
       List<SysDict> parentList = dictService.getDictList("datasourceType");
       List<RdbSource> rdbList = baseMapper.getAllDataSource();
       List<Map<String, Object>> result = Lists.newArrayList();
        for (SysDict d : parentList) {
            List<Map<String, Object>> childrenList = Lists.newArrayList();
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", d.getId());
            map.put("label", d.getLabel());
            map.put("value", d.getValue());
            for (RdbSource rdb : rdbList) {
                if ((rdb.getType().toString()).equals(d.getLabel())) {
                    Map<String, Object> mapd = Maps.newHashMap();
                    mapd.put("id", rdb.getId());
                    mapd.put("label", rdb.getName());
                    mapd.put("type", rdb.getType().toString());
                    map.put("parentId",d.getId());
                    childrenList.add(mapd);
                    map.put("children",childrenList);
                }
            }
            result.add(map);
        }
        return result;
    }


    @Override
    public IPage<Table> findTablePage(IPage<Table> page, Table table) {
       return baseMapper.findTablePage(page,table);
    }

    @Override
    public  List<Table> getPreViewTable(Table table) {
        List<Table> tableNames = new ArrayList<>();
        TableUtil tableUtil = new TableUtil();
        List<Table> existsTable = findExistsTableList(table);
        RdbSource rdbSource = rdbSourceService.getById(table);
        switch (rdbSource.getType().toString()) {
            case "Oracle":
                RdbSource oracleSource = rdbSourceService.getOracleSource(table);
                if (null == oracleSource) {
                    return tableNames;
                }
                try {
                    String schema = StringUtils.isNotBlank(oracleSource.getSchema()) ? oracleSource.getSchema() : oracleSource.getAccount();
                    tableNames = tableUtil.getTables(oracleSource.getType(), oracleSource.getJdbcUrl(),
                            oracleSource.getAccount(), oracleSource.getPwd(), schema.toUpperCase(), table);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "DB2":
                RdbSource db2Source = rdbSourceService.getDb2Source(table);
                if (null == db2Source) {
                    return tableNames;
                }
                try {
                    String schema = StringUtils.isNotBlank(db2Source.getSchema()) ? db2Source.getSchema() : db2Source.getAccount();
                    tableNames = tableUtil.getTables(db2Source.getType(), db2Source.getJdbcUrl(),
                            db2Source.getAccount(), db2Source.getPwd(), schema.toUpperCase(), table);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    if (null == rdbSource) {
                        return tableNames;
                    }
                    tableNames = tableUtil.getTables(rdbSource.getType(), rdbSource.getJdbcUrl(),
                            rdbSource.getAccount(), rdbSource.getPwd(), rdbSource.getAccount().toUpperCase(), table);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        for (int j = 0; j < existsTable.size(); j++) {
            for (int i = 0; i < tableNames.size(); i++) {
                if (tableNames.get(i).getName().equalsIgnoreCase(existsTable.get(j).getName()) && tableNames.get(i).getType().equals(existsTable.get(j).getType())) {
                    tableNames.remove(i);
                    break;
                }
            }
        }
        return tableNames;
    }

    /**
     * 表预览时候去除已经添加过的数据表
     *
     * @param table
     * @return List<Table>
     */
    public List<Table> findExistsTableList(Table table) {
        return this.baseMapper.findExistsTableList(table);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveTable(Table table){
        for (Map<String, String> tbList : table.getTableList()) {
            table.setName(tbList.get("name"));
            table.setType(TableKindEnum.valueOf(tbList.get("type")));
            table.setRemarks(tbList.get("remarks"));
            table.setCName(tbList.get("name"));
            table.setDbId(table.getDbId());
            table.setCreateBy(SecurityUtils.getUser().getId());
            table.setUpdateBy(SecurityUtils.getUser().getId());
            table.setCreateDate(LocalDateTime.now());
            table.setUpdateDate(LocalDateTime.now());
            table.setDelFlag(CommonConstants.STATUS_NORMAL);
            this.baseMapper.insert(table);

            Field field = new Field();
            field.setTable(table);
            List<Field> fieldList = fieldService.preField(field);
            if(CollectionUtils.isNotEmpty(fieldList)){
                fieldService.saveAll(fieldList);
            }
            table.setId("");
        }
           return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTableById(Table table){
        table.setUpdateBy(SecurityUtils.getUser().getId());
        table.setUpdateDate(LocalDateTime.now());
        return this.baseMapper.updateTableById(table);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeTableById(String id){
        this.fieldMapper.delete(new QueryWrapper<Field>().eq("table_Id", id));
        return this.baseMapper.deleteById(id)>0;
    }
}
