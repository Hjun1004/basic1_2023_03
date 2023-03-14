package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 개발자가 스프링부트에게 말한다.  HomeController는 컨트롤러이다.
public class HomeController {
    // @GetMapping("/home/main")
    // 개발자가 스프링부트에게 말한다.
    // /home/main을 포함한 요청이 오면 아래 메서드를 실행해줘
    @GetMapping("/home/main")

    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain(){
        return "안녕하세요.";
    }
}
