package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.Rq;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class MemberController {
    int memberId = 0;
    MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password, HttpServletRequest req, HttpServletResponse resp){
        Rq rq = new Rq(req,resp);
        if ( username == null || username.trim().length() == 0 ) {
            return RsData.of("F-3", "username(을)를 입력해주세요.");
        }

        if ( password == null || password.trim().length() == 0 ) {
            return RsData.of("F-4", "password(을)를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);

        if( rsData.isSuccess()){
            Member member = (Member)rsData.getData();
            rq.setCookie("loginedMemberId", member.getId());
        }

        return rsData;
    }

    @GetMapping("/member/me")
    @ResponseBody
    public RsData showMe(HttpServletRequest req, HttpServletResponse resp){

        Rq rq = new Rq(req, resp);
        long loginedMemberId = rq.getCookieAsLong("loginedMemberId", 0);

        if(loginedMemberId == 0){
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }

        Member members = memberService.findById(loginedMemberId);

        return RsData.of("S-1", "당신의 username은 %s 입니다.".formatted(members.getUsername()));
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    });
        }

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }

    @GetMapping("/member/logout2")
    @ResponseBody
    public RsData logout2(HttpServletRequest req, HttpServletResponse resp) {
        Rq rq = new Rq(req, resp);
        boolean cookieRemoved = rq.removeCookie("loginedMemberId");

        if(cookieRemoved == false) return RsData.of("F-5", "이미 로그아웃");
        return RsData.of("S-1", "로그아웃 되었습니다.");
    }
}
