package com.newproject.web.entity;


import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Study {
    private String studyName;
    private String studyAddress;
    private String studyDays;
    private String studySubject;
    private String status;
    private int numOfPeople;
    private Date registerDate;
}
