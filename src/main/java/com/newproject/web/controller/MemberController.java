package com.newproject.web.controller;

import com.newproject.web.entity.Member;
import com.newproject.web.model.MemberDto;
import com.newproject.web.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/index")
    public ModelAndView index() {
        List<Member> list = memberService.list();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("members", list);
        return mv;
    }

    @RequestMapping("/create")
    public String create(MemberDto dto) {
        memberService.insert(dto.getUsername(), dto.getPassword(), dto.getName(), dto.getEmail());
        return "redirect:index";
    }

//
//

}
