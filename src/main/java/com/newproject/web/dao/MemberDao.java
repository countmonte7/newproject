package com.newproject.web.dao;

import com.newproject.web.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
public class MemberDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.update("create table member(id int auto_increment, username varchar(255) not null," +
                "password varchar(255) not null, primary key(id))");
        jdbcTemplate.update("insert into member(username, password) values('jusekyung','1234')");
    }

    public void insert(String username, String password, String name, String email) {
        jdbcTemplate.update("insert into member(username, password, name, email) values(?,?,?,?);",
                username, password, name, email);
    }

    public List<Member> list() {
        return jdbcTemplate.query("select * from member;",
                (resultSet, i) -> new Member(resultSet));
    }


}
