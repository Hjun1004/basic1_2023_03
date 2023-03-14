package com.ll.basic1;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller // 개발자가 스프링부트에게 말한다.  HomeController는 컨트롤러이다.
public class HomeController { // 컨트롤러는 점원이다 고객의 요청마다 점원이 새로 뽑히지 않는것과 같다.
    // 컨트롤러는 프로그램이 실행될때 생성되고 종료될때까지 꺼지지 않는다.
    private int increase;
    People people;
    List<People> al;
    int id = 0;
    int ch = 0;

    public HomeController(){
        this.increase = 0;
        al = new ArrayList<>();
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

    // 이 함수와 아래 showReqAndRespV2 함수는 똑같이 작동한다.
    @GetMapping("/home/reqAndResp")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int age = Integer.parseInt(req.getParameter("age"));
        resp.getWriter().append("Hello, you are %d years old.".formatted(age));
    }

    // 이 방식이 가능한 이유는 스프링부트가 배후에서 처리를 해주기 때문이다.(이 방식이 코딩하기 더 편하다.)
    @GetMapping("/home/reqAndRespV2")
    @ResponseBody
    public String showReqAndRespV2(int age) {
        return "Hello, you are %d years old.".formatted(age);
    }

    @GetMapping("/home/cookie/increase")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int countInCookie = 0;

        if(req.getCookies() != null){
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(cookie -> cookie.getValue())
                    .mapToInt(cookie -> Integer.parseInt(cookie))
                    .findFirst()
                    .orElse(0);
        }

        int newCountInCookie = countInCookie + 1;
        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        return newCountInCookie;
    }

    @GetMapping("/home/plus")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // int a 는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화한 값이어야한다.
    // RequestParam는 생략가능
    public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
        return a + b;
    }

    @GetMapping("/home/returnBoolean")
    @ResponseBody
    public boolean showReturnBoolean() {
        return true;
    }

    @GetMapping("/home/returnDouble")
    @ResponseBody
    public double showReturnDouble() {
        return Math.PI;
    }

    @GetMapping("/home/returnIntArray")
    @ResponseBody
    public int[] showReturnIntArray() {
        int[] arr = new int[]{10, 20, 30};

        return arr;
    }

    @GetMapping("/home/returnIntList")
    @ResponseBody
    public List<Integer> showReturnIntList() {
        List<Integer> list = new ArrayList<>() {{
            add(10);
            add(20);
            add(30);
        }};

        return list;
    }

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap() {
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        return map;
    }

    @GetMapping("/home/returnCar")
    @ResponseBody
    public Car showReturnCar() {
        Car car = new Car(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        return car;
    }

    @GetMapping("/home/returnCarV2")
    @ResponseBody
    public CarV2 showReturnCarV2() {
        CarV2 car = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        car.setName(car.getName() + "V2");

        return car;
    }

    @GetMapping("/home/returnCarMapList")
    @ResponseBody
    public List<Map<String, Object>> showReturnCarMapList() {
        Map<String, Object> carMap1 = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        Map<String, Object> carMap2 = new LinkedHashMap<>() {{
            put("id", 2);
            put("speed", 200);
            put("name", "포르쉐");
            put("relatedIds", new ArrayList<>() {{
                add(5);
                add(6);
                add(7);
            }});
        }};

        List<Map<String, Object>> list = new ArrayList<>();

        list.add(carMap1);
        list.add(carMap2);

        return list;
    }

    @GetMapping("/home/returnCarList")
    @ResponseBody
    public List<CarV2> showReturnCarList() {
        CarV2 car1 = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        CarV2 car2 = new CarV2(2, 200, "포르쉐", new ArrayList<>() {{
            add(5);
            add(6);
            add(7);
        }});

        List<CarV2> list = new ArrayList<>();

        list.add(car1);
        list.add(car2);

        return list;
    }


    @GetMapping("/home/addPerson")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // RequestParam는 생략가능
    public String AddPerson(@RequestParam String name, @RequestParam int age){ //@RequestParam(defaultValue = "0") int a 이렇게 되면 브라우저에서 요청할 때
        People people = new People(name, age);
        System.out.println(people);
        al.add(people);
        return "%d번 사람이 추가되었습니다.".formatted(people.getId());
    }

    @GetMapping("/home/people")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // int a 는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화한 값이어야한다.
    // RequestParam는 생략가능
    public List<People> showPeople(){

        return al;
    }

    /* 내가 한거
    @GetMapping("/home/removePerson")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // int a 는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화한 값이어야한다.
    // RequestParam는 생략가능
    public String removePerson(int id){

        for(People p : al){
            if(p.getId() == id){
                al.remove(p);
                ch = 1;
            }
        }

        if(ch == 1){
            ch = 0;
            return "%d번 사람이 목록에서 삭제되었습니다.".formatted(id);
        }
        else{
            return "%d번 사람이 목록에서 존재안함.".formatted(id);
        }


    }*/

    @GetMapping("/home/removePerson")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // int a 는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화한 값이어야한다.
    // RequestParam는 생략가능
    public String removePerson(int id){
        boolean removed = al.removeIf(People -> People.getId() == id);

        if(removed == false){
            return "%d번 사람이 목록에서 존재안함.".formatted(id);
        }
        return "%d번 사람이 목록에서 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    //@ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // int a 는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화한 값이어야한다.
    // RequestParam는 생략가능
    public String modifyPerson(int id, @RequestParam String name, @RequestParam int age){ //@RequestParam(defaultValue = "0") int a 이렇게 되면 브라우저에서 요청할 때
        //boolean ch = al.stream().anyMatch(people -> people.getId() == id);
        People found = al.stream().filter(people -> people.getId() == id)
                        .findFirst()
                                .orElse(null);
        if(found == null) return "%d번 사람이 목록에서 존재안함.".formatted(id);

        /*System.out.println(ch);
        내가한 거
        if(ch == false){
            return "%d번 사람이 목록에서 존재안함.".formatted(id);
        }

        for(People p : al){
            if(p.getId() == id){
                p.setName(name);
                p.setAge(age);
            }
        }*/
        found.setName(name);
        found.setAge(age);

        return "%d번 사람이 수정되었습니다.".formatted(id);
    }


}

@AllArgsConstructor
@Getter
@ToString
class People{
    private static int lastId;
    private int id;
    @Setter
    private String name;
    @Setter
    private int age;

    static {
        lastId = 0;
    }
    People(String name, int age){
        this(++lastId, name, age);
    }
}

class Car {
    private final int id;
    private final int speed;
    private final String name;
    private final List<Integer> relatedIds;

    public Car(int id, int speed, String name, List<Integer> relatedIds) {
        this.id = id;
        this.speed = speed;
        this.name = name;
        this.relatedIds = relatedIds;
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }
}

@AllArgsConstructor
@Getter
class CarV2 {
    private final int id;
    private final int speed;
    @Setter
    private String name;
    private final List<Integer> relatedIds;
}