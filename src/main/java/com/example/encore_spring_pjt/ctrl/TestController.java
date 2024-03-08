package com.example.encore_spring_pjt.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.encore_spring_pjt.domain.BoardResponse;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List ; 
import java.util.ArrayList ; 

/*
Http : code(200 , 404(not found) , 405(bad request) , 500(NPE) ) 
@RequestMapping(path, method)
-- GetMapping(<a href="">)
-- PostMapping(<form action="" method="get|post">) 
@RestController (json + async)
@Controller (full browsing , rest API - @ResponseBody )
*/
@Controller
public class TestController {
    
    /*
    http://server_ip:port/
    return type : void, String, ModelAndView : full browsing 
    return type : xxxxxDTO , List , Map , Set , ResponseEntity : REST API Service
    */ 
    /* 
    @RequestMapping("/")
    public String index() {
        System.out.println(">>>>> TestController  path  / , callback function index()");
        return "index" ; 
    }
    */
    /* 
    @RequestMapping("/")
    public ModelAndView index() {
        System.out.println(">>>>> TestController  path  / , callback function index()");
        ModelAndView mv = new ModelAndView() ; 
        mv.setViewName("index");
        mv.addObject("msg", "hi~, JSP") ; 
        return mv ; 
    }
    */
    /* 
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println(">>>>> TestController  path  / , callback function index()");
        model.addAttribute("msg", "welcome To SpringBoot with JSP") ;
        return "index" ; 
    }
    @RequestMapping("/test")
    public void test() {
        System.out.println(">>>>> TestController  path  /test , callback function test()");
    }

    @GetMapping("/json")
    @ResponseBody
    public List<BoardResponse> json() {
        System.out.println(">>>>> TestController  path  /json , callback function json()");
        BoardResponse board = BoardResponse.builder()
                                .title("json title")
                                .content("json cocntent")
                                .writer("encore")
                                .build() ; 
        List<BoardResponse> lst = new ArrayList();
        lst.add(board);lst.add(board);lst.add(board);lst.add(board);

        return lst ;  
    }
    */
}




