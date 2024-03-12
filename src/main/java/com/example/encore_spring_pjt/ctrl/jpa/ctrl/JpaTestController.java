package com.example.encore_spring_pjt.ctrl.jpa.ctrl;

import org.springframework.web.bind.annotation.RestController;

import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import com.example.encore_spring_pjt.ctrl.jpa.service.JpaService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class JpaTestController {
    
    // 의존성주입(Service) - DI
    private final JpaService service ;

    // 사용자의 모든 정보 조회 GetMapping 
    @GetMapping(value = "/list" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<JpaEntity>> list() {
        System.out.println(">>>> debug JpaTestController client path /jpa/list");
        System.out.println("debug >>> serice ,  " + service ); 
        // service method call
        List<JpaEntity> list = service.findAll();
        return new ResponseEntity<List<JpaEntity>>(list, HttpStatus.OK); 
    }

    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JpaEntity> save(@RequestBody JpaEntity params) {
        System.out.println(">>>> debug JpaTestController client path /jpa/save");
        System.out.println("debug >>> params , " + params); 
        JpaEntity entity = service.save(params);
        return new ResponseEntity<JpaEntity>(entity, HttpStatus.OK); 
    }

    // 삭제 - PathVariable
    @DeleteMapping(value ="/delete/{seq}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> delete(@PathVariable("seq") Integer seq) {
        System.out.println(">>>> debug JpaTestController client path /jpa/delete/seq");
        System.out.println("debug >>> params , " + seq);
        service.delete(seq);   
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT) ;
    }

    
}






