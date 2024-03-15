package com.example.encore_spring_pjt.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;

import jakarta.servlet.http.HttpSession;



@Controller
public class IndexController {
    /* 
    @RequestMapping("/index.hanwha") 
    public String index(HttpSession session) {
        System.out.println("debug >>> IndexController client path : / "); 
        UserResponse response = (UserResponse)session.getAttribute("loginUser") ;
        System.out.println("debug >>> session Y/N , " + response); 
        if(response == null) {
            return "index" ; 
        } else {
            return "redirect:/board/list.hanwha" ; 
        }
        
    }
    */
    // 세션유무 다른 처리 방식으로 
    /* 
    @RequestMapping("/index.hanwha") 
    public String index(@SessionAttribute(required = false, name = "loginUser") UserResponse response) {
        System.out.println("debug >>> IndexController client path : / "); 
        System.out.println("debug >>> session Y/N , " + response); 
        if(response == null) {
            return "index" ; 
        } else {
            return "redirect:/board/list.hanwha" ; 
        }
        
    }
    */
    
    @RequestMapping("/index.hanwha") 
    public String index() {
        System.out.println("debug >>> IndexController client path : / "); 
        return "index" ; 
    }
}





