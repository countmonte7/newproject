package com.newproject.web.controller;

import com.newproject.web.entity.Member;
import com.newproject.web.model.MemberDto;
import com.newproject.web.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
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

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String create(MemberDto dto, HttpServletResponse response, HttpServletRequest request) throws IOException {
       int result = memberService.insert(dto.getUsername(), dto.getPassword(), dto.getName(), dto.getEmail());
        PrintWriter out = response.getWriter();
        if(result==0) { 
            out.println("<script>alert('이미 등록된 아이디입니다.');</script>");
       }else {
            out.println("<script>alert('회원가입 성공')</script>");
        }
       return "redirect:index";
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> login(@RequestBody Member member) {
        String username = member.getUsername();
        String password = member.getPassword();
//        log.info(username +"/" + password);
        HashMap<String, String> map = new HashMap<String, String>();
        int result = memberService.login(username, password);
        log.info("result : " + result);
        if(result == 0) {
            map.put("result", "notExist");
            return map;
        }else if(result == -1) {
            map.put("result", "passwordIncorrect");
            return map;
        }
            map.put("result", "success");
        return map;
    }

}
