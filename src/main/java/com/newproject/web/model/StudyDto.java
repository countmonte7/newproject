package com.newproject.web.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class StudyDto {
    private String username;
    private String studyName;
    private String studyAddress;
    private String studyDays;
    private String studySubject;
    private String status;
    private int numOfPeople;
    private Date registerDate;
}
