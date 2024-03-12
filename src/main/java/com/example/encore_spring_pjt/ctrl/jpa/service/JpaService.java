package com.example.encore_spring_pjt.ctrl.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.encore_spring_pjt.ctrl.jpa.dao.EncoreRepository;
import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import java.util.List ; 
import java.util.ArrayList ; 
@Service
public class JpaService {
    
    
    // JPA     : dao(interface) - Repository
    // Mybatis : dao(interface) - Mapper
    // 의존성 주입으로 Repository 받아야 한다.

    @Autowired(required = true)
    private EncoreRepository encoreRepository ;  

    public List<JpaEntity> findAll() {
        System.out.println("debug >>>> service findAll , " + encoreRepository);
        List<JpaEntity> list = encoreRepository.findAll();
        /* 
        for(JpaEntity e : list) {
            System.out.println(e) ; 
        }
        */
        /* forEach - lambda  
        List<JpaEntity> list = new ArrayList<JpaEntity>();
        encoreRepository.findAll().forEach(e -> list.add(e) );  
        */
        return list ;   
    }

    public JpaEntity save(JpaEntity params) {
        System.out.println("debug >>>> service findAll , " + encoreRepository);
        // JPA - save() ;
        encoreRepository.save(params) ;
        return params ; 
    }

    // JPA - delete함수는 : deleteById() 
    public void delete(Integer seq) {
        System.out.println("debug >>>> service delete , " + seq);
        encoreRepository.deleteById(seq) ; 
    }

}



