package com.example.encore_spring_pjt.ctrl.jpa.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "ENCORE_BOARD_TBL")
public class BoardEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 
    private Integer idx ; 
    
    private String  title ; 
    private String  content ;
    private String  writer ;
    @Column(name = "view_cnt")
    private Integer viewCnt ;
    @Column(name = "notice_yn")
    private Boolean noticeYn ;
    @Column(name = "secret_yn") 
    private Boolean secretYn ;
    @Column(name = "delete_yn") 
    private boolean deleteYn ; 
    
    private LocalDateTime insertTime ; 
    private LocalDateTime updateTime ;  
}
