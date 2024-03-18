package com.example.encore_spring_pjt.aop.ctrl;

import org.springframework.web.bind.annotation.RestController;

import com.example.encore_spring_pjt.aop.domain.DataRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/aop") 
public class AopTestController {
    
    @GetMapping("/test01")
    public ResponseEntity<Void> test01() {
        System.out.println("ctrl test01() core concern");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT) ;
    }
    @GetMapping("/test02/{msg}")
    public ResponseEntity<Void> test02(@PathVariable("msg") String msg ) {
        System.out.println("ctrl test02() core concern , " + msg) ; 
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT) ;
    }
    @PostMapping("/test03")
    public ResponseEntity<DataRequest> test03(@RequestBody DataRequest param) {
        System.out.println("ctrl test03() core concern , ") ; 
        return new ResponseEntity<DataRequest>(param , HttpStatus.OK);
    }
    @GetMapping("/test04")
    public ResponseEntity<DataRequest> test04(@RequestBody DataRequest param) throws Exception {
        System.out.println("ctrl test04() core concern , ") ; 
        throw new Exception("예외발생");
    }
    



    
}
