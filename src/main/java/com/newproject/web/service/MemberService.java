package com.newproject.web.service;

import com.newproject.web.dao.MemberDao;
import com.newproject.web.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
public class MemberService {

    private MemberDao memberDao;

    @Transactional
    public int insert(String username, String password, String name, String email) {
        return memberDao.insert(username, password, name, email);
    }

    public List<Member> list() {
        return  memberDao.list();
    }

    public int login(String username, String password) {
        return memberDao.login(username, password);
    }

    public Member getUserInfo(String username) {
        return memberDao.getUserInfo(username);
    }
}
