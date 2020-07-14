package com.newproject.web.dao;

import com.newproject.web.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    private Member member;

    @Autowired
    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int insert(String username, String password, String name, String email) {
        var existId = jdbcTemplate.query("select * from member where username=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
            }
        }, new ResultSetExtractor<Long>() {
            public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()) {
                    return resultSet.getLong(1);
                }
                return null;
            }
        });
        if(existId == null) {
            return jdbcTemplate.update("insert into member(username, password, name, email) values(?,?,?,?);",
                    username, password, name, email);
        }else {
           return 0;
        }
    }

    public List<Member> list() {
        return jdbcTemplate.query("select * from member;",
                (resultSet, i) -> new Member(resultSet));
    }

    public int login(String username, String password) {
        try{
            String result = jdbcTemplate.queryForObject("select password from member where username=?",
                    new Object[]{username}, String.class);
            if(result != null && result != "") {
                if(password.equals(result)) {

                    return  1;
                }
            }
        }catch (EmptyResultDataAccessException e) {
            return 0;
        }
        return -1;
    }


    public Member getUserInfo(String username) {
        return jdbcTemplate.query("select * from member where username=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
            }
        }, new ResultSetExtractor<Member>() {
            @Override
            public Member extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    member = new Member(id, username, password, name, email);
                }
                return member;
            }
        });
    }
}
