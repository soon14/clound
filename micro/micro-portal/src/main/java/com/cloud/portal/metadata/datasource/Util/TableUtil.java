package com.cloud.portal.metadata.datasource.Util;


import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.metadata.datasource.constant.DataSourceEnum;
import com.cloud.portal.metadata.datasource.exception.RdbmsException;
import com.cloud.portal.metadata.standardfields.constant.TableKindEnum;
import com.cloud.portal.metadata.standardfields.entity.Field;
import com.cloud.portal.metadata.standardfields.entity.Table;
import com.cloud.portal.metadata.standardfields.service.TableService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GXX
 * @date Created in 2018/2/6 0006 下午 14:33
 * @description:
 * @modified By: wengshij
 */
public class TableUtil {
    protected static Logger logger = LoggerFactory.getLogger(TableUtil.class);
    private static PreparedStatement sta = null;
    private static ResultSet rs = null;
    private static Connection conn = null;
    private final String SCHEMA_PATTERN = "schemaPattern";

    private final String TABLENAME_PATTERN = "tableNamePattern";
    /**
     * types - 表类型数组; "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM";
     * null表示包含所有的表类型;可包含单字符通配符("_"),或多字符通配符("%");
     */
    private final String[] TYPES = {"TABLE", "VIEW","SYNONYM"};
    @Autowired
    private TableService tableService;

    /**
     * @param sql sql语句
     * @param obj 参数
     * @return 数据集合
     */
    public static ResultSet query(Connection conn, String sql, Object... obj) {
        try {
            sta = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    sta.setObject(i + 1, obj[i]);
                }
            }
            rs = sta.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }

    /**
     * 获取不同数据源表名预览信息
     *
     * @param dataBaseTypeEnum 数据源类型
     * @param url              jdbc地址
     * @param user             用户名
     * @param pass             密码
     * @return 表list以及条数count
     * @throws Exception
     */
    public List getTables(DataSourceEnum dataBaseTypeEnum,
                          String url, String user, String pass, String schema, Table table) throws Exception {
        Connection connection = null;
        List<Table> tableNames = new ArrayList();
        ResultSet resultSet = null;

        try {
            connection = DbUtil.connect(dataBaseTypeEnum, url, user, pass);
            DatabaseMetaData dbmd = connection.getMetaData();
            Map<String, String> pattern = getPattern(dataBaseTypeEnum, schema, null);
            resultSet = dbmd.getTables(null, pattern.get(SCHEMA_PATTERN), pattern.get(TABLENAME_PATTERN), TYPES);
            while (resultSet.next()) {
                Table tableReturn = new Table();
                tableReturn.setName(resultSet.getString("TABLE_NAME"));
                String tableType = resultSet.getString("TABLE_TYPE");
                if (tableType.contains(" ")) {
                    tableType = tableType.split(" ")[1];
                }
                tableType = tableType.substring(0, 1) + tableType.substring(1).toLowerCase();
                tableReturn.setType(TableKindEnum.valueOf(tableType));
                tableReturn.setRemarks(resultSet.getString("REMARKS"));
                if (hasCondition(table, tableReturn)) {
                    tableNames.add(tableReturn);
                }

            }

        } catch (RdbmsException e) {
            throw new Exception(e.getErrorCode().getDescription());

        } finally {
            resultSet.close();
            DbUtil.closeDBResources(null, connection);
        }
        return tableNames;
    }

    /**
     * 查询条件判断
     *
     * @param table
     * @param tableReturn
     * @return
     */
    private boolean hasCondition(Table table, Table tableReturn) {
        if (null == table || null == tableReturn) {
            return true;
        }
        if (StringUtils.isNotBlank(table.getName()) && StringUtils.isNotBlank(tableReturn.getName())) {
            if (!tableReturn.getName().toUpperCase().contains(table.getName().toUpperCase())) {
                return false;
            }
        }
        if (null != table.getType() && null != tableReturn.getType()) {
            if (!tableReturn.getType().toString().toUpperCase().contains(table.getType().toString().toUpperCase())) {
                return false;
            }
        }
        if (StringUtils.isNotBlank(table.getRemarks())) {
            if (StringUtils.isBlank(tableReturn.getRemarks()) || !tableReturn.getRemarks().contains(table.getRemarks())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 参数获取
     *
     * @param dataBaseTypeEnum
     * @param schema
     * @param tableNamePattern
     * @return
     */
    private Map<String, String> getPattern(DataSourceEnum dataBaseTypeEnum, String schema, String tableNamePattern) {
        Map<String, String> map = new HashMap<>(2);
        map.put(SCHEMA_PATTERN, null);
        map.put(TABLENAME_PATTERN, "%");
        if (dataBaseTypeEnum == DataSourceEnum.Oracle || dataBaseTypeEnum == DataSourceEnum.DB2) {
            map.put(SCHEMA_PATTERN, StringUtils.isNotBlank(schema) ? schema.toUpperCase() : schema);
            map.put(TABLENAME_PATTERN, StringUtils.isNotBlank(tableNamePattern) ? tableNamePattern.toUpperCase() : "%");
        } else if (dataBaseTypeEnum == DataSourceEnum.MySql) {
            map.put(SCHEMA_PATTERN, schema);
            map.put(TABLENAME_PATTERN, StringUtils.isNotBlank(tableNamePattern) ? tableNamePattern : "%");
        } else {
            map.put(SCHEMA_PATTERN, null);
            map.put(TABLENAME_PATTERN, StringUtils.isNotBlank(tableNamePattern) ? tableNamePattern : "%");
        }

        return map;
    }


    /**
     * 获取不同数据源表字段预览信息
     */
    public List<Field> preField(DataSourceEnum dataBaseTypeEnum, String url, String user, String pass,
                                String tableName, String tbId, String schema) {
        List<Field> fieldList = new ArrayList<Field>();
        try {

            conn = DbUtil.connect(dataBaseTypeEnum, url, user, pass);
            DatabaseMetaData dbmd = conn.getMetaData();
            Map<String, String> pattern = getPattern(dataBaseTypeEnum, schema, tableName);

            ResultSet pkRSet = dbmd.getPrimaryKeys(null, pattern.get(SCHEMA_PATTERN), pattern.get(TABLENAME_PATTERN));
            Map<String, String> pkMap = new HashMap<>(16);
            while (pkRSet.next()) {
                pkMap.put((String.valueOf(pkRSet.getObject(4)).toUpperCase()), String.valueOf(pkRSet.getObject(4)));
            }
            ResultSet rsColumn = null;
            rsColumn = dbmd.getColumns(null, pattern.get(SCHEMA_PATTERN), pattern.get(TABLENAME_PATTERN), "%");
            while (rsColumn.next()) {
                Field field = new Field();
                field.setTableId(tbId);
                Table table=new Table();
                table.setId(tbId);
                field.setTable(table);
                field.setName(rsColumn.getString("COLUMN_NAME"));
                field.setCName(rsColumn.getString("COLUMN_NAME"));
                field.setType(rsColumn.getString("TYPE_NAME"));
                field.setLength(rsColumn.getInt("COLUMN_SIZE"));
                field.setRemarks(rsColumn.getString("REMARKS")==null?"":rsColumn.getString("REMARKS"));
                field.setNotNull(rsColumn.getString("NULLABLE"));
                field.setDelFlag("0");
                field.setCreateDate(LocalDateTime.now());
                field.setUpdateDate(field.getCreateDate());
                field.setCreateBy(SecurityUtils.getUser().getId());
                field.setUpdateBy(field.getCreateBy());
                if (StringUtils.isNotBlank(field.getName()) && pkMap.get(field.getName().toUpperCase()) != null) {
                    field.setPk("0");
                } else {
                    field.setPk("1");
                }
                fieldList.add(field);
            }

            pkRSet.close();
            rsColumn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeDBResources(null, conn);
        }
        return fieldList;
    }

    /**
     * 获取不同数据源表字段预览信息(同义词读取不出数据表字段)
     */
    public List<Field> preField2(DataSourceEnum dataBaseTypeEnum, String url, String user, String pass,
                                String tableName, String tbId, String schema) {
        List<Field> fieldList = new ArrayList<Field>();
        try {
            String zhujian = "";
            conn = DbUtil.connect(dataBaseTypeEnum, url, user, pass);
            PreparedStatement ps = null;
            StringBuilder sql = new StringBuilder();
            StringBuilder sqlForPk = new StringBuilder();
            sqlForPk.append("select col.* from all_constraints con,all_cons_columns col where con.CONSTRAINT_NAME=col.CONSTRAINT_NAME and con.CONSTRAINT_TYPE='P' and col.TABLE_NAME='").append(tableName.toUpperCase()).append("'");
            sql.append("select * from all_tab_columns t where t.TABLE_NAME='").append(tableName.toUpperCase()).append("'order by t.COLUMN_ID");
            ResultSet rsColumn = null;
            ps = conn.prepareStatement(sqlForPk.toString());
            rsColumn = ps.executeQuery();
            while (rsColumn.next()) {
                zhujian = rsColumn.getString("COLUMN_NAME");
            }
            ps = conn.prepareStatement(sql.toString());
            DatabaseMetaData dbmd = conn.getMetaData();
            rsColumn = ps.executeQuery();
            while (rsColumn.next()) {
                Field field = new Field();
                field.setTableId(tbId);
                Table table=new Table();
                table.setId(tbId);
                field.setTable(table);
                field.setName(rsColumn.getString("COLUMN_NAME"));
                field.setCName(rsColumn.getString("COLUMN_NAME"));
                field.setType(rsColumn.getString("DATA_TYPE"));
                field.setLength(rsColumn.getInt("DATA_LENGTH"));
                field.setRemarks("");
                field.setNotNull(rsColumn.getString("NULLABLE"));
                field.setDelFlag("0");
                field.setCreateDate(LocalDateTime.now());
                field.setUpdateDate(field.getCreateDate());
                field.setCreateBy(SecurityUtils.getUser().getId());
                field.setUpdateBy(field.getCreateBy());
                if(field.getName().equals(zhujian)){
                    field.setPk("0");
                }
                else {
                    field.setPk("1");
                }
                fieldList.add(field);
            }

            ps.close();
            rsColumn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeDBResources(null, conn);
        }
        return fieldList;
    }

}
