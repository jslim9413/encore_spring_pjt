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


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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

    // 사용자의 한명 정보 조회 GetMapping 
    @GetMapping(value = "/find/{seq}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<JpaEntity>> find(@PathVariable("seq") Integer seq) {
        System.out.println(">>>> debug JpaTestController client path /jpa/find/seq");
        // service method call
        Optional<JpaEntity> entity = service.find(seq);
        System.out.println("debug >>> ctrl find result entity , " + entity );
        System.out.println("debug >>> ctrl find result get()  , " + entity.get() );
        return new ResponseEntity<Optional<JpaEntity>>(entity , HttpStatus.OK); 
    }

    // 기본키로 사용자정보 수정
    // raw 데이터 형식으로 데이터 전달되었다고 가정 
    
    // @PutMapping(value = "/update" , produces = {MediaType.APPLICATION_JSON_VALUE})
    // public ResponseEntity update(@RequestBody JpaEntity entity) {
    // or 
    @PutMapping(value = "/update/{seq}/{id}/{pwd}/{name}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update(JpaEntity entity) {
        System.out.println(">>>> debug JpaTestController client path /jpa/update");
        System.out.println("debug >>> params , " + entity) ; 
        service.update(entity) ; 

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT) ;
    }

    @GetMapping(value = "/findName/{name}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<JpaEntity>> findName(@PathVariable("name") String name) {
        System.out.println(">>>> debug JpaTestController client path /jpa/findName/name");
        System.out.println("debug >>> params name , " + name); 
        // service method call
        List<JpaEntity> entity = service.findName(name);
        return new ResponseEntity<List<JpaEntity>>(entity , HttpStatus.OK); 
    }

    @GetMapping(value = "/findNameLike/{name}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<JpaEntity>> findNameLike(@PathVariable("name") String name) {
        System.out.println(">>>> debug JpaTestController client path /jpa/findNameLike/name");
        System.out.println("debug >>> params name , " + name); 
        // service method call
        // like %, _
        List<JpaEntity> entity = service.findNameLike("%"+name+"%");
        return new ResponseEntity<List<JpaEntity>>(entity , HttpStatus.OK); 
    }
     
}






