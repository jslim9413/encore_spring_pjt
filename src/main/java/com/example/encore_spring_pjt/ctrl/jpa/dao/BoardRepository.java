package com.example.encore_spring_pjt.ctrl.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.encore_spring_pjt.ctrl.jpa.domain.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
}
    
