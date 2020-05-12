package com.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> selectAll(String sql,RowMapper<?> rowMapper,Object[] value) throws  Exception{
        Connection connection = getConnection();

        PreparedStatement pre = cteateprepareStatement(connection, sql);

        ResultSet resultSet = executeQuery(pre, value);

        List<?> list = parseResultSet(resultSet, rowMapper);

        connection.close();
        pre.close();
        resultSet.close();
        return list;


    }

    private List<?> parseResultSet(ResultSet resultSet, RowMapper<?> rowMapper) throws Exception {
        List<Object> data=new ArrayList<Object>();
        int i=0;
        while (resultSet.next()){
            data.add(rowMapper.mapRow(resultSet,i));
            i++;
        }
        return  data;

    }


    private ResultSet executeQuery(PreparedStatement preparedStatement,Object[] value) throws SQLException {
        for (int i = 0; i < value.length; i++) {

            preparedStatement.setObject(i,value[i]);

        }
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    private PreparedStatement cteateprepareStatement(Connection connection,String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
