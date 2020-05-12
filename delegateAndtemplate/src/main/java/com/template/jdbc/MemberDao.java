package com.template.jdbc;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class MemberDao {
    private DataSource dataSource;

    public MemberDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> select() throws Exception {
        String sql="select * from user";
        List<?> list = new JdbcTemplate(dataSource).selectAll(sql, new RowMapper<Member>() {
            public Member mapRow(ResultSet resultSet, int num) throws Exception {
                Member member = new Member();
                member.setAddr(resultSet.getString("addr"));
                member.setUsername(resultSet.getString("userName"));
                return member;
            }
        }, null);
        return list;
    }
}
