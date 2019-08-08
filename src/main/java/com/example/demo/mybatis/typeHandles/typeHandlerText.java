package com.example.demo.mybatis.typeHandles;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//指定jdbc类型数据 添加完成后 在配置了mybatis.type-handlers-package=com.example.demo.mybatis.typeHandles 就可以让所有此类型都走这个类
@MappedJdbcTypes(JdbcType.VARCHAR)
public class typeHandlerText extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {

        // TODO Auto-generated method stub
        ps.setString(i, parameter);
        System.out.println("jdbcType:"+jdbcType);

    }
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {

        return rs.getString(columnName)+"alreadyhandle1";
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return rs.getString(columnIndex)+"alreadyhandle2";
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return cs.getString(columnIndex)+"alreadyhandle3";
    }
}
