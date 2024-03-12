package com.example.encore_spring_pjt.ctrl.jpa.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity(name = "ENCORE_JPA_TEST_TBL")
public class JpaEntity {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment    
    private Integer seq  ; 
    @Column    
    private String  id   ; 
    @Column    
    private String  pwd  ;
    @Column    
    private String  name ; 

}
