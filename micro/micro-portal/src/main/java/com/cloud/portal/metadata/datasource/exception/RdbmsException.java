package com.cloud.portal.metadata.datasource.exception;


import com.cloud.portal.metadata.datasource.constant.Constant;
import com.cloud.portal.metadata.datasource.constant.DataSourceEnum;

/**
 * @author vlong
 * @date Created in 2017/12/20 17:25
 * description: 关系型数据库异常类
 * modified By:
 */
public class RdbmsException extends AppException {
    public RdbmsException(ErrorCode errorCode, String message){
        super(errorCode,message);
    }

    public static AppException asConnException(DataSourceEnum dataBaseTypeEnum, Exception e, String userName, String dbName){
        if (dataBaseTypeEnum.equals(DataSourceEnum.MySql)){
            DBUtilErrorCode dbUtilErrorCode = mySqlConnectionErrorAna(e.getMessage());
            if (dbUtilErrorCode == DBUtilErrorCode.MYSQL_CONN_DB_ERROR && dbName !=null ){
                return AppException.asAppException(dbUtilErrorCode,"该数据库名称为："+dbName+" 具体错误信息为："+e);
            }
            if (dbUtilErrorCode == DBUtilErrorCode.MYSQL_CONN_USERPWD_ERROR ){
                return AppException.asAppException(dbUtilErrorCode,"该数据库用户名为："+userName+" 具体错误信息为："+e);
            }
            return AppException.asAppException(dbUtilErrorCode," 具体错误信息为："+e);
        }

        if (dataBaseTypeEnum.equals(DataSourceEnum.Oracle)){
            DBUtilErrorCode dbUtilErrorCode = oracleConnectionErrorAna(e.getMessage());
            if (dbUtilErrorCode == DBUtilErrorCode.ORACLE_CONN_DB_ERROR && dbName != null){
                return AppException.asAppException(dbUtilErrorCode,"该数据库名称为："+dbName+" 具体错误信息为："+e);
            }
            if (dbUtilErrorCode == DBUtilErrorCode.ORACLE_CONN_USERPWD_ERROR ){
                return AppException.asAppException(dbUtilErrorCode,"该数据库用户名为："+userName+" 具体错误信息为："+e);
            }
            return AppException.asAppException(dbUtilErrorCode," 具体错误信息为："+e);
        }
        return AppException.asAppException(DBUtilErrorCode.CONN_DB_ERROR," 具体错误信息为："+e);
    }

    public static DBUtilErrorCode mySqlConnectionErrorAna(String e){
        if (e.contains(Constant.MYSQL_DATABASE)){
            return DBUtilErrorCode.MYSQL_CONN_DB_ERROR;
        }

        if (e.contains(Constant.MYSQL_CONNEXP)){
            return DBUtilErrorCode.MYSQL_CONN_IPPORT_ERROR;
        }

        if (e.contains(Constant.MYSQL_ACCDENIED)){
            return DBUtilErrorCode.MYSQL_CONN_USERPWD_ERROR;
        }

        return DBUtilErrorCode.CONN_DB_ERROR;
    }

    public static DBUtilErrorCode oracleConnectionErrorAna(String e){
        if (e.contains(Constant.ORACLE_DATABASE)){
            return DBUtilErrorCode.ORACLE_CONN_DB_ERROR;
        }

        if (e.contains(Constant.ORACLE_CONNEXP)){
            return DBUtilErrorCode.ORACLE_CONN_IPPORT_ERROR;
        }

        if (e.contains(Constant.ORACLE_ACCDENIED)){
            return DBUtilErrorCode.ORACLE_CONN_USERPWD_ERROR;
        }

        return DBUtilErrorCode.CONN_DB_ERROR;
    }


}
