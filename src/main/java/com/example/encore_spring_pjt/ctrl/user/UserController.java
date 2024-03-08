package com.example.encore_spring_pjt.ctrl.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/user") // http:// server ip  : port /user
public class UserController {

    @PostMapping("/login.hanwha") // http:// server ip  : port /user/login.hanwha
    public String login(UserRequest params) {
    // public String login(String id , String pwd) {
    // public String login(@RequestParam(name = "id") String id , 
    //                     @RequestParam(name = "pwd") String pwd) { 
    
        System.out.println("debug UserController client path /user/login.hanwha");
        System.out.println("param value >>> " + params.getId());
        System.out.println("param value >>> " + params.getPwd());  
        return "redirect:/board/list.hanwha" ; 
    }
}

