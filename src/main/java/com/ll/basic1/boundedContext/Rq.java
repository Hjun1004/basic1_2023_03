package com.ll.basic1.boundedContext;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@AllArgsConstructor
@RequestScope // 이 객체는 매 요청마다 새성된다.
@Component
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

//    public Rq(HttpServletRequest req, HttpServletResponse resp){
//        this.req = req;
//        this.resp = resp;
//    }
    public void setCookie(Object name, Object value){
        resp.addCookie(new Cookie(name + "", value + ""));
    }

    public boolean removeCookie(Object name){
        boolean isRemoved = false;
        if (req.getCookies() != null) {
            Cookie cookie = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(name + ""))
                    .findFirst()
                    .orElse(null);

            if(cookie != null){
                cookie.setMaxAge(0);
                resp.addCookie(cookie);

                return true;
            }
            /*Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(name + ""))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    });

            return Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(name + ""))
                    .count()>0;*/
        }

        return false;
    }

    public String getCookie(String name, String defaultValue){
        if(req.getCookies() == null) return defaultValue;

        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .map(cookie -> cookie.getValue())
                .findFirst()
                .orElse(defaultValue);
    }

    public long getCookieAsLong(String name, long defaultValue){
        String value = getCookie(name, null);

        if(value == null){
            return defaultValue;
        }

        try{
            return Long.parseLong(value);
        }
        catch (Exception e){
            return defaultValue;
        }
    }

    public void setCookie(String name, String value){
        resp.addCookie(new Cookie(name, value));
    }

    public void setCookie(String name, Long value){
        setCookie(name, value + "");
    }


}
