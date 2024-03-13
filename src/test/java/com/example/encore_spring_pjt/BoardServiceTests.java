package com.example.encore_spring_pjt;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;

import jakarta.annotation.Resource;

// DI - Autowired , Resource
@SpringBootTest
public class BoardServiceTests {
    /* 
    @Autowired
    private BoardService postServiceImpl ;
    */
    @Resource(name = "board")
    private BoardService service ;

    @Test
    public void serviceSaveTest() {
        System.out.println("debug Test -> " + service ) ; 
        /* Builder를 사용하지 않는 레거시(legacy) 형식 
        BoardRequest request = new BoardRequest() ;
        request.setTitle("service 수업");
        request.setContent("service를 이용한 mapper 연결");
        request.setWriter("encore"); 
        request.setNoticeYn(true);
        request.setSecretYn(true); 
        */
        BoardRequest request = BoardRequest.builder()
                                .title("service")
                                .content("mapper")
                                .writer("encore")
                                .noticeYn(true)
                                .secretYn(true)
                                .build() ; 

        System.out.println("debug >>>> before save " + request);
        Integer idx = service.saveBoard(request) ;  
        System.out.println("입력한 데이터의 키 값을 출력 : " + idx ); 
    }

    @Test
    public void serviceFindTest() {
        BoardRequest request = BoardRequest.builder()
                                .idx(3)
                                .build() ; 

        Optional<BoardResponse> response = service.findBoard(request);
        System.out.println("debug find result >>>>> ");
        System.out.println(response);  
    }
    @Test
    public void serviceUpdateTest() {
        System.out.println("debug serviceUpdateTest >>>>>>>>>>>>> ") ; 
        BoardRequest request = BoardRequest.builder()
                                .idx(3)
                                .title("수정")
                                .content("변경되네")
                                .writer("jslim")
                                .build() ; 

        
        Integer idx = service.updateBoard(request);
        System.out.println(idx + "번지의 데이터를 수정하였습니다. " ); 
    }
    @Test
    public void serviceDeleteTest() {
        System.out.println("debug serviceDeleteTest >>>>>>>>>>>>> ") ; 
        BoardRequest request = BoardRequest.builder()
                                .idx(3)
                                .build() ; 
        Integer idx = service.deleteBoard(request) ;
        System.out.println(idx + "번지의 데이터를 삭제하였습니다. " ); 
    }

    @Test
    public void serviceFindAllTest() {
        System.out.println("debug findAll >>>>>>>>>>>>> ") ; 
        List<BoardResponse> lst = service.listBoard();
        for(BoardResponse response : lst) {
            System.out.println(response); 
        }
    }
    @Test
    public void serviceCnt() {
        System.out.println("debug cnt >>>>>>>>>>>>> ") ; 
        Integer count = service.cntBoard();
        System.out.println("총 데이터의 개수는 " + count + "개 입니다."); 
    }
}
