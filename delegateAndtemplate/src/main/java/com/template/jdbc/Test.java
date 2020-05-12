package com.template.jdbc;

import javax.sql.DataSource;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
       // DataSource dataSource=new MysqlDataSource()
        MemberDao dao = new MemberDao(null);
        List<?> select = dao.select();
    }
}
