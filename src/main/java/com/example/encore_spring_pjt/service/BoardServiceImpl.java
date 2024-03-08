package com.example.encore_spring_pjt.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;

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

    @Override
    public BoardResponse findBoard(BoardRequest params) {
        System.out.println("debug >>> service findBoard ");
        boardMapper.updateByCnt(params); 
        return boardMapper.findByIdx(params);
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
    
}
