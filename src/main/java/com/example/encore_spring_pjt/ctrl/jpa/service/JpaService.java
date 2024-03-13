package com.example.encore_spring_pjt.ctrl.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.encore_spring_pjt.ctrl.jpa.dao.EncoreRepository;
import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;
import java.util.List ;
import java.util.Optional;
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

    public Optional<JpaEntity> find(Integer seq) {
        System.out.println("debug >>>> service find , " + seq);
        // Optional<JpaEntity> entity = encoreRepository.findById(seq);
        Optional<JpaEntity> entity = encoreRepository.findByCustomerId(seq);
        return entity ;
    }
    public void update(JpaEntity entity) {
        System.out.println("debug >>>> service update , " + entity); 
        // JPA - Update (find - save) 
        /* 
        Optional<JpaEntity> data = encoreRepository.findById(entity.getSeq());
        System.out.println("debug >>> service update , " + data); 
        
        if( !data.isEmpty() ) {
            data.get().setSeq(entity.getSeq()); 
            data.get().setId(entity.getId()); 
            data.get().setPwd(entity.getPwd()); 
            data.get().setName(entity.getName()); 
            encoreRepository.save(data.get()); 
        }
        */

        // JPQL 방식 
        encoreRepository.updateEntity(entity.getSeq(), 
                                      entity.getId(),
                                      entity.getPwd(),
                                      entity.getName()); 

        
    }

    public List<JpaEntity> findName(String name) {
        System.out.println("debug >>>> service findName , " + name);
        List<JpaEntity> entity = encoreRepository.findByName(name);
        return entity ;
    }

    public List<JpaEntity> findNameLike(String name) {
        System.out.println("debug >>>> service findName , " + name);
        List<JpaEntity> entity = encoreRepository.findByNameLike(name);
        return entity ;
    }


}



