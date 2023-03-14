package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 개발자가 스프링부트에게 말한다.  HomeController는 컨트롤러이다.
public class HomeController { // 컨트롤러는 점원이다 고객의 요청마다 점원이 새로 뽑히지 않는것과 같다.
    // 컨트롤러는 프로그램이 실행될때 생성되고 종료될때까지 꺼지지 않는다.
    private int increase;

    public HomeController(){
        this.increase = 0;
    }

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

    @GetMapping("/home/main2")

    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain2(){
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")

    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain3(){
        return "즐거웠습니다.";
    }

    // @GetMapping("/home/increase")
    // 개발자가 스프링부트에게 말한다.
    // /home/increase를 포함한 요청이 오면 아래 메서드를 실행해줘
    @GetMapping("/home/increase")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public int showIncrese(){ // 리턴되는 int 값은 String화 되어서 고객에게 전달한다.(스프링부트가 알아서 String으로 바꿔준다.)
        return increase++; //계속 새로고침을 해서 증가된 increase가 보여지겠지만 int 자료형 범위 내 숫자까지만 올라간다.
    }


}
