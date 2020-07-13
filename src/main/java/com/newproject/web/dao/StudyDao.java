package com.newproject.web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;

public class StudyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudyDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public int startStudy(String username, String studyName, String studySubject,
                          String studyAddress, String studyStatus, String studyDays,
                          Date studyRegisterDate, int numOfPeople) {
        jdbcTemplate.update("insert into study values(?,?,?,?,?,?,?)",
                username, studyName, studySubject, studyAddress, studyStatus,
                studyDays, studyRegisterDate, numOfPeople);
        return 0;
    }
}
