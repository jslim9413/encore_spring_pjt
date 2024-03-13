package com.example.encore_spring_pjt.ctrl.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.encore_spring_pjt.ctrl.jpa.domain.JpaEntity;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface EncoreRepository extends JpaRepository<JpaEntity, Integer>{

    @Query("SELECT U FROM ENCORE_JPA_TEST_TBL U WHERE U.seq = :seq")
    public Optional<JpaEntity> findByCustomerId(@Param("seq") Integer seq); 


    // JAP - Update(find-save)
    // 사용자정의 메서드를 정의해서 편하게 작업해 본다면? - JPQL
    // @Query - Select
    // @Query - DML(Insert, Update, Delete) with @Modifying
    // @Param - :xxxx 데이터 바인딩 
    @Transactional
    @Modifying
    @Query("UPDATE ENCORE_JPA_TEST_TBL U "  
          +"SET U.id= :id , U.pwd = :pwd , U.name = :name "
          +"WHERE U.seq = :seq") 
    public void updateEntity(@Param("seq")  Integer seq,
                             @Param("id")   String id,
                             @Param("pwd")  String pwd,
                             @Param("name") String name) ; 


    // findByXXXX 컬럼명(property)
    // findByPwd() , findByName() , findByNameLike()
    
    // findByIdAndPwd(String id, String pwd) : where id = ? and pwd = ? 
    // findByIdOrPwd(String id, String pwd)  : where id = ? or  pwd = ? 
    // findByIdOrderBYAsc()
    // http://localhost:8888/jpa/findName/임정섭 : PathValue
    // select * from table where name = '임정섭' ; 
    public List<JpaEntity> findByName(String name); 

    // http://localhost:8888/jpa/findNameLike/정섭 : PathValue
    public List<JpaEntity> findByNameLike(String name); 


}








