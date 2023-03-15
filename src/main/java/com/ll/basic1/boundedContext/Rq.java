package com.ll.basic1.boundedContext;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public class Rq {
    private HttpServletResponse resp;
    private HttpServletRequest req;
    public Rq(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }
    public void setCookie(Object name, Object value){
        resp.addCookie(new Cookie(name + "", value + ""));
    }
    public void removeCookie(Object name){
        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(name + ""))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    });
        }


    }
}
