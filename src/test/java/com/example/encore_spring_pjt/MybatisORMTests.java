package com.example.encore_spring_pjt;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class MybatisORMTests {
    
    @Autowired
    private BoardMapper boardMapper ;

    @Test
    public void ormSave() {
        System.out.println("debug mapper >>>>>>>>>>> " + boardMapper) ;
        BoardRequest request = BoardRequest.builder()
                                .title("service")
                                .content("mapper")
                                .writer("encore")
                                .noticeYn(true)
                                .secretYn(true)
                                .build() ; 
        boardMapper.save(request); 
        System.out.println("debug >>>>>> save success "); 
    }

    @Test
    public void ormFind() throws Exception {
        System.out.println("debug finder >>>>>>>>>>>>> ") ; 
        BoardRequest request = BoardRequest.builder()
                                .idx(3)
                                .build() ; 
        Optional<BoardResponse> response = boardMapper.findByIdx(request);
        System.out.println("debug find result >>>>> ");
        System.out.println(response);  
        System.out.println(">>>>>>>>>>>>>> json ") ; 
        String boardJson = new ObjectMapper().
                                registerModule(new JavaTimeModule()).
                                writeValueAsString(response) ; 
        System.out.println(boardJson) ; 
    }

    @Test
    public void ormUpdate() {
        System.out.println("debug update >>>>>>>>>>>>> ") ; 
        BoardRequest request = BoardRequest.builder()
                                .idx(3)
                                .title("수정")
                                .content("변경되네")
                                .writer("jslim")
                                .build() ; 
        boardMapper.updateByIdx(request);  
        System.out.println("debug >>>> update success ");
    }

    @Test
    public void ormCnt() {
        System.out.println("debug cnt >>>>>>>>>>>>> ") ; 
        Integer count = boardMapper.count();
        System.out.println("debug >>>> count success " + count); 
    }

    @Test
    public void ormDelete() {
        System.out.println("debug delete >>>>>>>>>>>>> ") ; 
        BoardRequest request = BoardRequest.builder()
                                .idx(3)
                                .build() ; 
        boardMapper.deleteByIdx(request) ; 
        System.out.println(">>>>>> delete success "); 
    }
    @Test
    public void ormFindAll() {
        System.out.println("debug findAll >>>>>>>>>>>>> ") ; 
        List<BoardResponse> lst = boardMapper.findAll();
        for(BoardResponse response : lst) {
            System.out.println(response); 
        }
    }
}






