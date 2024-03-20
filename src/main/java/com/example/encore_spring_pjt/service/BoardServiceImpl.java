package com.example.encore_spring_pjt.service;


import java.util.Collections ;
import java.util.List;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.ctrl.board.util.Pagination;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

// BoardService boardServiceImpl = new BoardServiceImpl();
@Service("board") 
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    /* 
    @Autowired
    private BoardMapper boardMapper ; 
    */
    private final BoardMapper boardMapper ; 
    
    @Transactional
    @Override
    public Integer saveBoard(BoardRequest params) {
        System.out.println("debug >>>> board service saveBoard : " + boardMapper);
        boardMapper.save(params); 
        return params.getIdx() ; 
    }

    @Transactional
    @Override
    public Optional<BoardResponse> findBoard(BoardRequest params) {
        System.out.println("debug >>> service findBoard ");
        boardMapper.updateByCnt(params); 
        Optional<BoardResponse> response = boardMapper.findByIdx(params);
        return response ;
    }
    
    @Transactional
    @Override
    public void findBoardUpCnt(BoardRequest params) {
        System.out.println("debug >>> service findBoardNotCnt ");
        boardMapper.updateByCnt(params); 
    }

    @Transactional
    @Override
    public Integer updateBoard(BoardRequest params) {
        System.out.println("debug >>> service updateBoard "); 
        boardMapper.updateByIdx(params);
        return params.getIdx() ; 
    }
    
    @Transactional
    @Override
    public Integer deleteBoard(BoardRequest params) {
        System.out.println("debug >>> service deleteBoard "); 
        boardMapper.deleteByIdx(params);
        return params.getIdx() ; 
    }

    /* 페이지처리로 변경  
    @Override
    public List<BoardResponse> listBoard() {
        System.out.println("debug >>> service listBoard "); 
        return boardMapper.findAll();
    }
    
    @Override
    public Integer cntBoard() {
        System.out.println("debug >>> service cntBoard "); 
        return boardMapper.count() ; 
    }
    */
    /* 
    @Override
    public List<BoardResponse> listBoard(PageDTO params) {
        System.out.println("debug >>> service listBoard ");
        System.out.println("debug >>> service params  , " + params); 
        return boardMapper.findAll(params);
    }
    */
    @Override
    public PageResponse<BoardResponse> listBoard(PageDTO params) {
        // 페이지처리와 페이지네이션을 위해서는 전체 게시글 수가 필요
        int recordCnt = boardMapper.count(params); 
        if( recordCnt <= 0 ) {
            return new PageResponse<>(Collections.emptyList(), null) ;  
        }
        // Pagination 객체를 이용해서 계산을 하기위해서는 params 객체를 넘겨줘야한다.
        Pagination pagination = new Pagination(recordCnt, params);
        params.setPagination(pagination); 
        List<BoardResponse> list = boardMapper.findAll(params);
        return new PageResponse<>(list, pagination) ; 
    }
    @Override
    public Integer cntBoard(PageDTO params) {
        System.out.println("debug >>> service cntBoard "); 
        return boardMapper.count(params) ; 
    }
    @Override
    public Optional<BoardResponse> findBoardNotView(BoardRequest params) {
        System.out.println("debug >>> service findBoard ");
        Optional<BoardResponse> response = boardMapper.findByIdx(params);
        return response ;
    }

    
    
}
