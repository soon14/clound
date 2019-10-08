package com.cloud.portal.metadata.datasource.constant;

/**
 * @author vlong
 * @date Created in 2017/12/20 17:25
 * description: 数据源类型
 * modified By:
 */
public enum DataSourceEnum {
    MySql(DataSourceKindEnum.RDB,"com.mysql.jdbc.Driver"),
    Oracle(DataSourceKindEnum.RDB,"oracle.jdbc.OracleDriver"),
    SQLServer(DataSourceKindEnum.RDB,"com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    PostGreSQL(DataSourceKindEnum.RDB,"org.postgresql.Driver"),
    DB2(DataSourceKindEnum.RDB,"com.ibm.db2.jcc.DB2Driver"),
    File(DataSourceKindEnum.FILE,""),
    FTP(DataSourceKindEnum.FILE,""),
    S3 (DataSourceKindEnum.NoSQL,""),
    HDFS(DataSourceKindEnum.NoSQL,""),
    Kafka(DataSourceKindEnum.STREAM,"");

    private DataSourceKindEnum type;
    private String driverClassName;

    DataSourceEnum( DataSourceKindEnum type, String driverClassName) {
        this.type = type;
        this.driverClassName = driverClassName;
    }


    public String getDriverClassName() {
        return this.driverClassName;
    }

    public DataSourceKindEnum getType(){
        return this.type;
    }

}
