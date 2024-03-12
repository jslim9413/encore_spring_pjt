package com.example.encore_spring_pjt.ctrl.valid;

import org.springframework.web.bind.annotation.RestController;

import com.example.encore_spring_pjt.ctrl.valid.domain.UserRequestDTO;
import com.oracle.wls.shaded.org.apache.xml.utils.StringBufferPool;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/valid")
public class RestValidController {
    
    // json {key:value, key:value, ....} 데이터 전달이 되었을 때를 가정 
    // BindingResult : 유효성 검증에 실패한 메시지를 모아서 관리하는 객체 
    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody UserRequestDTO params, 
                                                    BindingResult bindingResult) {
        System.out.println("debug RestValidController client path /valid/create/");
        System.out.println("debug >>> params , " + params ); 
        /* 
        if( params.getAge() > 200 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(params); 
        }
        */
        if( bindingResult.hasErrors() ) {
            System.out.println("debug >>> have a error message");
            // StringBuffer buffer = new StringBuffer() ;
            List<ObjectError> list = bindingResult.getAllErrors();
            Map<String, String> map = new HashMap<>() ; 

            for(int idx = 0 ; idx < list.size() ; idx++) {
                FieldError field = (FieldError)list.get(idx); 
                String     msg   = list.get(idx).getDefaultMessage();
                System.out.println("debug >>> " + field.getField() + "\t" + msg);
                map.put(field.getField() , msg);
            }
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST) ; 
        } else {
            System.out.println("debug >>> do not have a error message");
        }
        return new ResponseEntity(params, HttpStatus.OK) ; 
    }
    
}

