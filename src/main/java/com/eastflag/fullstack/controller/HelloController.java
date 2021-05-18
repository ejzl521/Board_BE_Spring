package com.eastflag.fullstack.controller;

import com.eastflag.fullstack.domain.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController // json, xml등의 리소스를 리턴해줄 수 있도록 해주는 기능
public class HelloController {

    @RequestMapping("/hello") //받아들이는 url의 경로, (get, post) 둘다 허용
    public String hello() {
        return "Hello test";
    }

    @GetMapping("/hello2") // get만 허용
    public String hello2() {
        return "Hello GET test";
    }

    @GetMapping("/hello3")
    public String hello3(@RequestParam("name") String name) {//query parameter에서 넘어온 key값을 할당
        return "Hello" + name;
    }
    @GetMapping("/hello32/{name}")
    public String hello32(@PathVariable String name) { //uri 파라미터터
        return "Hello" + name;
    }
    @PostMapping("/hello33") //Post방식의 query Parameter로 데이터 보내기
    public String hello33(@RequestParam("name") String name) {
        return "Hello" + name;
    }
    @PostMapping("/hello4")
    public String hello4(@RequestParam String name) {
        return "Hello " + name;
    }
    @PostMapping("/hello5")
    public ResultVO hello5(@RequestParam("name") String name) { //객체 리턴하면 자바 객체가 json으로 변환됨.
        ResultVO result = new ResultVO(0, name);
        return result;
    }
    @PostMapping("/hello6")
    public ResultVO hello6(@RequestBody ResultVO result) {
        return result;
    }

}