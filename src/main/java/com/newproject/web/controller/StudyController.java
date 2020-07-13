package com.newproject.web.controller;

import com.newproject.web.model.StudyDto;
import com.newproject.web.service.StudyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class StudyController {

    @Autowired
    StudyService studyService;

    @RequestMapping("/createStudy")
    public String createStudyPage() {
        return "createStudy";
    }

    @RequestMapping(value = "/startStudy", method = RequestMethod.POST)
    public int startStudy(StudyDto studyDto) {
        studyService.startStudy(studyDto.getUsername(), studyDto.getStudyName(), studyDto.getStudySubject(),
                studyDto.getStudyAddress(), studyDto.getStatus(), studyDto.getStudyDays(),
                studyDto.getRegisterDate(), studyDto.getNumOfPeople());
        return 0;
    }
}
