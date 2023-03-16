package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.Rq;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@AllArgsConstructor
public class MemberController {
    //int memberId = 0;
    private MemberService memberService;
    private Rq rq;

//    public MemberController(MemberService memberService){
//        this.memberService = memberService;
//    }

    @GetMapping("/member/login")
    @ResponseBody
    public String showLogin(){
        if(rq.isLogined()){
            return """
                    <h1>이미 로그인 되었습니다.<h1>
                    """.stripIndent();
        }

        return """
                <h1>로그인</h1>
                <form method="GET" action="/member/doLogin" target="_blank">
                <input type="text" name="username" placeholder="아이디">
                <input type="text" name="password" placeholder="비밀번호">
                <input type="submit" value="로그인">
                </form>
                """.stripIndent();
        //순서대로 /member/doLogin 후 ?가 붙는다.
        // 그 후 username = 입력받는 아이디 입력하면 자동으로 &이 붙는다.
        // 그리고 password = 입력받는 비밀번호
        // 버튼으로 로그인 해도되고 <button type="submit">로그인</button>

    }


    @GetMapping("/member/doLogin")
    @ResponseBody
    public RsData login(String username, String password){
        if ( username == null || username.trim().length() == 0 ) {
            return RsData.of("F-3", "username(을)를 입력해주세요.");
        }

        if ( password == null || password.trim().length() == 0 ) {
            return RsData.of("F-4", "password(을)를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);

        if( rsData.isSuccess()){
            Member member = (Member)rsData.getData();
            rq.setSession("loginedMemberId", member.getId());
        }

        return rsData;
    }

    @GetMapping("/member/me")
    @ResponseBody
    public RsData showMe(){
        long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);

        if(loginedMemberId == 0){
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }

        Member members = memberService.findById(loginedMemberId);

        return RsData.of("S-1", "당신의 username은 %s 입니다.".formatted(members.getUsername()));
    }

    @GetMapping("/member/logout2")
    @ResponseBody
    public RsData logout2(HttpServletRequest req, HttpServletResponse resp) {
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

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout1() {
        boolean sessionRemoved = rq.removeSession("loginedMemberId");

        if(sessionRemoved == false) return RsData.of("F-5", "이미 로그아웃");
        return RsData.of("S-1", "로그아웃 되었습니다.");
    }

    // 디버깅용 함수
//    @GetMapping("/member/session")
//    @ResponseBody
//    public String showSession() {
//        return rq.getSessionDebugContents().replaceAll("\n", "<br>");
//    }
}
