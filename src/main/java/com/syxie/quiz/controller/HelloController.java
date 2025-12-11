package com.syxie.quiz.controller;

import com.syxie.quiz.model.Result;
import com.syxie.quiz.model.user.SimpleUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
//    public String hello() {
//        System.out.println("hello");
//        return "Hello world!";
//    }
    public Result hello(){
        System.out.println("hello");
        return Result.success("hello");
    }

    @RequestMapping("/simpleParam") //表示如果接收到浏览器的/simpleParam，就执行下面的getParam()方法；
//    public String getParam(String name, Integer age) {
//        System.out.println(name + ":" + age);
//        return "ok";
//    }
    public Result simpleParam(String name,Integer age){
        System.out.println(name + ":" + age);
        return Result.success("simpleParam");
    }


    @RequestMapping("/simpleUser") //表示如果接收到浏览器的/simpleUser，就执行下面的getUser()方法；
//    public Object getUser(SimpleUser user) {
//        System.out.println(user);
//        SimpleUser simpleUser=new SimpleUser();
//        simpleUser.setAge(user.getAge()+10);
//        simpleUser.setName(user.getName());
//        return simpleUser;
//    }
    public Result getUser(SimpleUser user){
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setAge(user.getAge()+10);
        simpleUser.setName(user.getName());
        return Result.success(simpleUser);
    }
}
