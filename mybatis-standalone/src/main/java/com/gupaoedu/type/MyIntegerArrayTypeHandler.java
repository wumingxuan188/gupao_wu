package com.gupaoedu.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: qingshan
 *
 */
public class MyIntegerArrayTypeHandler extends BaseTypeHandler<Integer[]>{
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Integer[] integers, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,integers.toString());
    }

    @Override
    public Integer[] getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String dbRes = resultSet.getString(s);
        return    wrapData(dbRes);
    }

    @Override
    public Integer[] getNullableResult(ResultSet resultSet, int i) throws SQLException {

        return    wrapData( resultSet.getString(i));
    }

    @Override
    public Integer[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return  wrapData( callableStatement.getString(i));
    }

    //包装结果,把字符串转换成int数组
    public Integer[] wrapData(String data){
        String[] datas = data.split(",");
        Integer[] returnData=new Integer[datas.length];
        for (int i = 0; i < datas.length; i++) {
            returnData[i]= Integer.valueOf(datas[i]);
        }
        return returnData;
    }
}