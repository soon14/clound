package com.cloud.portal.metadata.datasource.Util;
import com.cloud.portal.metadata.datasource.constant.Constant;
import com.cloud.portal.metadata.datasource.constant.DataSourceEnum;
import com.cloud.portal.metadata.datasource.exception.RdbmsException;
import com.cloud.portal.metadata.standardfields.constant.TableKindEnum;
import com.cloud.portal.metadata.standardfields.entity.Table;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author vlong
 * @date Created in 2017/12/20 17:25
 * description:关系型数据库工具类
 * modified By:
 */
public class DbUtil {

    static Logger logger = LoggerFactory.getLogger(DbUtil.class);

    public static boolean testConnWithoutRetry(DataSourceEnum dataBaseTypeEnum,
                                               String url, String user, String pass) throws Exception {
        Connection connection = null;

        try {
            connection = connect(dataBaseTypeEnum, url, user, pass);
            return true;
        } catch (RdbmsException e) {
            throw new Exception(e.getErrorCode().getDescription());

        } finally {
            DbUtil.closeDBResources(null, connection);
        }
    }

    protected static synchronized Connection connect(DataSourceEnum dataBaseTypeEnum,
                                                   String url, String user, String pass) {
        return connect(dataBaseTypeEnum, url, user, pass, String.valueOf(Constant.SOCKET_TIMEOUT_INSECOND * 1000));
    }

    private static synchronized Connection connect(DataSourceEnum dataBaseTypeEnum,
                                                   String url, String user, String pass, String socketTimeout) {
        Properties prop = new Properties();
        prop.put("user", user);
        prop.put("password", pass);

        if (dataBaseTypeEnum == DataSourceEnum.Oracle) {
            //oracle.net.READ_TIMEOUT for jdbc versions < 10.1.0.5 oracle.jdbc.ReadTimeout for jdbc versions >=10.1.0.5
            // unit ms
            prop.put("oracle.jdbc.ReadTimeout", socketTimeout);
        }
        else if (dataBaseTypeEnum == DataSourceEnum.MySql) {
            //oracle.net.READ_TIMEOUT for jdbc versions < 10.1.0.5 oracle.jdbc.ReadTimeout for jdbc versions >=10.1.0.5
            // unit ms
            prop.put("mysql.jdbc.ReadTimeout", socketTimeout);
        }
        else if (dataBaseTypeEnum == DataSourceEnum.SQLServer) {
            //oracle.net.READ_TIMEOUT for jdbc versions < 10.1.0.5 oracle.jdbc.ReadTimeout for jdbc versions >=10.1.0.5
            // unit ms
            prop.put("sqlserver.jdbc.ReadTimeout", socketTimeout);
        }

        return connect(dataBaseTypeEnum, url, prop);
    }

    private static synchronized Connection connect(DataSourceEnum dataBaseTypeEnum,
                                                   String url, Properties prop) {
        try {
            Class.forName(dataBaseTypeEnum.getDriverClassName());
            DriverManager.setLoginTimeout(Constant.TIMEOUT_SECONDS);
            return DriverManager.getConnection(url, prop);
        } catch (Exception e) {
            throw RdbmsException.asConnException(dataBaseTypeEnum, e, prop.getProperty("user"), null);
        }
    }

    public static void closeDBResources(ResultSet rs, Statement stmt,
                                        Connection conn) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException unused) {
            }
        }

        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException unused) {
            }
        }

        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException unused) {
            }
        }
    }
    public static void closeDBResources(Statement stmt, Connection conn) {
        closeDBResources(null, stmt, conn);
    }

    /**
     * 获取不同数据源表名预览信息
     * @param dataBaseTypeEnum 数据源类型
     * @param url jdbc地址
     * @param user 用户名
     * @param pass 密码
     * @param pageNo 当前页码
     * @param pageSize 当前页显示条数
     * @return 表list以及条数count
     * @throws Exception
     */
    public List getTables(DataSourceEnum dataBaseTypeEnum,
                                               String url, String user, String pass,int pageNo,int pageSize,Table table) throws Exception {
        Connection connection = null;
        List countAndName = new ArrayList<>();
        List<Table> tableNames = new ArrayList();
        int tablesCount = 0;
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        StringBuilder sqlForCount = new StringBuilder();
        sqlForCount.append("select count(1) from dba_tab_comments dt where dt.owner='")
                .append(user)
                .append("' ");
        if(StringUtils.isNotBlank(table.getName())){
            sqlForCount.append("and dt.table_name like '%")
                    .append(table.getName())
                    .append("%' ");
        }
        /*if(table.getType()!=null){
            if(StringUtils.isNotBlank(table.getType().name)){
                sqlForCount.append("and dt.table_type like '%")
                        .append(table.getType().name())
                        .append("%' ");
            }
        }*/
        if(StringUtils.isNotBlank(table.getRemarks())){
            sqlForCount.append("and dt.comments like '%")
                    .append(table.getRemarks())
                    .append("%' ");
        }
        sqlForCount.append(" and dt.table_name not in (select d.table_name from (select * from dba_tab_comments  where owner='")
                .append(user)
                .append("' ) d inner join recyclebin r on d.table_name = r.object_name)");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select row_.*, rownum rownum_ from (select * from dba_tab_comments dt where dt.owner='")
                .append(user)
                .append("' ");
        if(StringUtils.isNotBlank(table.getName())){
            sql.append("and dt.table_name like '%")
                    .append(table.getName())
                    .append("%' ");
        }
        /*if(table.getType()!=null){
            if(StringUtils.isNotBlank(table.getType().name)){
                sql.append("and dt.table_type like '%")
                        .append(table.getType().name())
                        .append("%' ");
            }
        }*/
        if(StringUtils.isNotBlank(table.getRemarks())){
            sql.append("and dt.comments like '%")
                    .append(table.getRemarks())
                    .append("%' ");
        }
                sql.append(" and dt.table_name not in (select d.table_name from (select * from dba_tab_comments  where owner='")
                .append(user)
                .append("' ) d inner join recyclebin r on d.table_name = r.object_name)) row_ where rownum <= ")
                .append(pageNo*pageSize)
                .append(") where rownum_ > ")
                .append((pageNo-1)*pageSize);
        try {
            connection = connect(dataBaseTypeEnum, url, user, pass);
            ps = connection.prepareStatement(sql.toString());
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                int i = 0;
                Table tableReturn = new Table();
                tableReturn.setName(resultSet.getString("TABLE_NAME"));
                tableReturn.setType(TableKindEnum.valueOf(resultSet.getString("TABLE_TYPE")));
                tableReturn.setRemarks(resultSet.getString("COMMENTS"));
                tableNames.add(tableReturn);
                i++;
            }
            ps = connection.prepareStatement(sqlForCount.toString());
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                tablesCount = resultSet.getInt("COUNT(1)");
            }
        } catch (RdbmsException e) {
            throw new Exception(e.getErrorCode().getDescription());

        } finally {
            resultSet.close();
            ps.close();
            DbUtil.closeDBResources(null, connection);
        }
        countAndName.add(tablesCount);
        countAndName.add(tableNames);
        return countAndName;
    }

    /**
     * 获取不同数据源获取用户列表
     * @param dataBaseTypeEnum
     * @param url
     * @param user
     * @param password
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<String> getOwners(DataSourceEnum dataBaseTypeEnum,String url,
                                  String user,String password,String sql) throws Exception {
        Connection connection = null;
        List<String> owners = new ArrayList();
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        try {
            connection = connect(dataBaseTypeEnum,url,  user, password);
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                if(isExistColumn(resultSet,"owner")){
                    String owner = resultSet.getString("owner");
                    owners.add(owner);
                }
            }
        } catch (RdbmsException e) {
            throw new Exception(e.getErrorCode().getDescription());

        } finally {
            if(resultSet!=null){
                resultSet.close();
            }
           if(ps!=null){
            ps.close();
           }
            DbUtil.closeDBResources(null, connection);
        }
        return owners;
    }

    public static boolean isExistColumn(ResultSet result,String columnName){
        try {
            if(result.findColumn(columnName)>0){
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            System.out.println(DbUtil.testConnWithoutRetry(DataSourceEnum.Oracle, "jdbc:oracle:thin:@10.2.20.114:1521:orcl", "WEB_ETL", "webetl"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
