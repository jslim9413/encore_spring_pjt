package com.example.encore_spring_pjt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.encore_spring_pjt.ctrl.jpa.dao.BoardRepository;
import com.example.encore_spring_pjt.ctrl.jpa.domain.BoardEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl {
    
    private final BoardRepository boardRepository;

    public List<BoardEntity> listBoard() {
        System.out.println("debug >>> service listBoard "); 
        List<BoardEntity> list = boardRepository.findAll() ;
        return list ;
    }
}

