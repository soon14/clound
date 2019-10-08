package com.cloud.common.data.handler;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * @author wengshij
 * @Date Created in 2019/04/09 8:39
 * Description: 处理oracle BLOB 字段 转 String 类型、mysql 数据库可忽略
 * Modified By:
 */
@MappedJdbcTypes(JdbcType.BLOB)
public class BlobTypeHandler extends BaseTypeHandler<String> {
    private static final String DEFAULT_CHARSET = "utf-8";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    String parameter, JdbcType jdbcType) throws SQLException {
        ByteArrayInputStream bis;
        try {
            bis = new ByteArrayInputStream(parameter.getBytes(DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
        ps.setBinaryStream(i, bis, parameter.length());
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return getBlob(rs.getBlob(columnName));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return getBlob(cs.getBlob(columnIndex));
    }


    @Override
    public String getNullableResult(ResultSet arg0, int arg1) throws SQLException {
        return null;
    }

    private String getBlob(Blob blob) throws SQLException {
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
    }
}
