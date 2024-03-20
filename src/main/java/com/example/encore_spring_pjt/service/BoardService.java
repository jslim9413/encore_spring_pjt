package com.example.encore_spring_pjt.service;

import java.util.Optional;

import java.util.Map;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse; 
public interface BoardService {
    public Integer saveBoard(BoardRequest params);
    
    public Optional<BoardResponse> findBoard(BoardRequest params); 
    // 조회수 중복방지 메서드 추가 
    public Optional<BoardResponse> findBoardNotView(BoardRequest params); 

    public void findBoardUpCnt(BoardRequest params);
    public Integer updateBoard(BoardRequest params);
    public Integer deleteBoard(BoardRequest params);

    // 페이지처리로 매개변수타입 추가  
    // public List<BoardResponse> listBoard();     
    // public Integer cntBoard() ; 
    // public List<BoardResponse> listBoard(PageDTO params);      
    
    public PageResponse<BoardResponse> listBoard(PageDTO params);      
    public Integer cntBoard(PageDTO params) ;  


    
    

}

