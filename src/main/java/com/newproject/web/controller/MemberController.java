package com.newproject.web.controller;

import com.newproject.web.entity.Member;
import com.newproject.web.model.MemberDto;
import com.newproject.web.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(MemberDto dto, Model model) throws IOException {
       int result = memberService.insert(dto.getUsername(), dto.getPassword(), dto.getName(), dto.getEmail());
       if(result==0) {
            model.addAttribute("msg", "error");
       }else {
           model.addAttribute("msg", "success");
       }
       return "redirect:index";
    }

//
//

}
