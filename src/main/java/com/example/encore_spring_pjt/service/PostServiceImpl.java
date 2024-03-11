package com.example.encore_spring_pjt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;

@Service("post")
public class PostServiceImpl implements BoardService{
    
    @Autowired
    private BoardMapper boardMapper ; 
    
    @Override
    public Integer saveBoard(BoardRequest params) {
        System.out.println("debug >>>> post service saveBoard : " + boardMapper);
        return null ; 
    }

    @Override
    public Optional<BoardResponse> findBoard(BoardRequest params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBoard'");
    }

    @Override
    public Integer updateBoard(BoardRequest params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBoard'");
    }

    @Override
    public Integer deleteBoard(BoardRequest params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBoard'");
    }

    @Override
    public List<BoardResponse> listBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listBoard'");
    }

    @Override
    public Integer cntBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cntBoard'");
    }
    
}
