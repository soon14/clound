

package com.cloud.portal.metadata.standardfields.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.metadata.datasource.Util.TableUtil;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.datasource.service.RdbSourceService;
import com.cloud.portal.metadata.standardfields.entity.Field;
import com.cloud.portal.metadata.standardfields.entity.Table;
import com.cloud.portal.metadata.standardfields.mapper.FieldMapper;
import com.cloud.portal.metadata.standardfields.mapper.TableMapper;
import com.cloud.portal.metadata.standardfields.service.FieldService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表字段
 * @modified By:
 */
@Slf4j
@Service
@AllArgsConstructor
public class FieldServiceImpl extends ServiceImpl<FieldMapper, Field> implements FieldService {
@Autowired
private RdbSourceService rdbSourceService;
private TableMapper tableMapper;

    @Override
    public IPage<Field> findFieldPage(IPage<Field> page, Field field) {
        return baseMapper.findFieldPage(page,field);
    }

    @Override
    public List<Field> preField(Field field) {
        if(StringUtils.isNotBlank(field.getTableId())){
            Table table = this.tableMapper.selectById(field.getTableId());
            field.setTable(table);
        }
        List<Field> fieldList = new ArrayList<>();
        TableUtil tableUtil = new TableUtil();
        List<Field> existsFieldList=findExistsFieldList(field);
        RdbSource rdbSource = rdbSourceService.getById(field.getTable());
        switch (rdbSource.getType().toString()) {
            case "Oracle":
                RdbSource oracleSource = rdbSourceService.getOracleSource(field.getTable());
                if (null == oracleSource) {
                    return fieldList;
                }
                try {
                    Table tb = tableMapper.selectById(field.getTable().getId());
                    String schema = StringUtils.isNotBlank(oracleSource.getSchema()) ? oracleSource.getSchema() : oracleSource.getAccount();
                    if(StringUtils.isNotBlank(tb.getType().name()) && tb.getType().name().equals("Synonym")){
                        fieldList = tableUtil.preField2(oracleSource.getType(), oracleSource.getJdbcUrl(),
                                oracleSource.getAccount(), oracleSource.getPwd(), field.getTable().getName().toUpperCase(), field.getTable().getId(), schema.toUpperCase());
                    }
                    else {
                        fieldList = tableUtil.preField(oracleSource.getType(), oracleSource.getJdbcUrl(),
                                oracleSource.getAccount(), oracleSource.getPwd(), field.getTable().getName().toUpperCase(), field.getTable().getId(), schema.toUpperCase());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "DB2":
                RdbSource db2Source =  rdbSourceService.getDb2Source(field.getTable());
                if (null == db2Source) {
                    return fieldList;
                }
                try {
                    String schema = StringUtils.isNotBlank(db2Source.getSchema()) ? db2Source.getSchema() : db2Source.getAccount();
                    fieldList = tableUtil.preField(db2Source.getType(), db2Source.getJdbcUrl(),
                            db2Source.getAccount(), db2Source.getPwd(), field.getTable().getName().toUpperCase(), field.getTable().getId(), schema.toUpperCase());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                if (null == rdbSource) {
                    return fieldList;
                }
                try {
                    fieldList = tableUtil.preField(rdbSource.getType(), rdbSource.getJdbcUrl(),
                            rdbSource.getAccount(), rdbSource.getPwd(), field.getTable().getName(), field.getTable().getId(), rdbSource.getAccount());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        for (int j = 0; j < existsFieldList.size(); j++) {
            for (int i = 0; i < fieldList.size(); i++) {
                if (fieldList.get(i).getName().equalsIgnoreCase(existsFieldList.get(j).getName()) && fieldList.get(i).getType().equals(existsFieldList.get(j).getType())) {
                    fieldList.remove(i);
                    break;
                }
            }
        }
        return fieldList;
    }

    /**
     * 字段预览时候去除已经添加过的表字段
     *
     * @return List<Field>
     */
    public List<Field> findExistsFieldList(Field field) {
        field.setTableId(field.getTable().getId());
        return this.baseMapper.findExistsFieldList(field);
    }

    /**
     * 保存所有字段信息、更新或者插入
     * @param fieldList
     */
    @Override
    public boolean saveAll(List<Field> fieldList){
        if(!CollectionUtils.isEmpty(fieldList)){
            this.baseMapper.saveAll(fieldList);
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveAllField(Field field){
        List<Field> fieldList = new ArrayList<>();
        for (Field fd : field.getFieldList()) {
            Field cloneFile= field.clone();
            cloneFile.setTableId(field.getTableId());
            Table table=new Table();
            table.setId(field.getTableId());
            cloneFile.setTable(table);
            cloneFile.setName(fd.getName());
            cloneFile.setCName(fd.getCName());
            cloneFile.setType(fd.getType());
            cloneFile.setLength(Integer.valueOf(fd.getLength()));
            cloneFile.setRemarks(fd.getRemarks()==null?"":fd.getRemarks());
            cloneFile.setNotNull(fd.getNotNull());
            cloneFile.setPk(fd.getPk());
            cloneFile.setDelFlag("0");
            cloneFile.setCreateDate(LocalDateTime.now());
            cloneFile.setUpdateDate(LocalDateTime.now());
            cloneFile.setCreateBy(SecurityUtils.getUser().getId());
            cloneFile.setUpdateBy(SecurityUtils.getUser().getId());
            fieldList.add(cloneFile);
        }
        return saveAll(fieldList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveField(Field field){
        field.setCreateBy(SecurityUtils.getUser().getId());
        field.setUpdateBy(SecurityUtils.getUser().getId());
        field.setCreateDate(LocalDateTime.now());
        field.setUpdateDate(LocalDateTime.now());
        field.setDelFlag(CommonConstants.STATUS_NORMAL);
        return this.save(field);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateFieldById(Field field){
        field.setUpdateBy(SecurityUtils.getUser().getId());
        field.setUpdateDate(LocalDateTime.now());
        return this.baseMapper.updateFieldById(field);
    }
}
