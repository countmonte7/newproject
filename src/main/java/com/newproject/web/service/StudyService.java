package com.newproject.web.service;

import com.newproject.web.dao.StudyDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@AllArgsConstructor
public class StudyService {

    @Autowired
    StudyDao studyDao;

    public int startStudy(String username, String studyName, String studySubject, String studyAddress,
                          String studyStatus, String studyDays, Date studyRegisterDate,
                          int numOfPeople) {
        studyDao.startStudy(username, studyName, studySubject, studyAddress,
                studyStatus, studyDays, studyRegisterDate, numOfPeople);
        return 0;
    }
}
