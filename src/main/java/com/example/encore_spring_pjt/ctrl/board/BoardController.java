package com.example.encore_spring_pjt.ctrl.board;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.encore_spring_pjt.ctrl.board.util.PageDTO;
import com.example.encore_spring_pjt.ctrl.board.util.PageResponse;
import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;
import com.example.encore_spring_pjt.service.BoardServiceImpl;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/board") // http:// serverip : port / board

public class BoardController {

    @Resource(name = "board" )
    private BoardService service ; 

    /* 페이지 처리로 변경됨 부분임.
    @RequestMapping("/list.hanwha") // http:// serverip : port / board / list.hanwha
    public String list(Model model) {
        System.out.println("debug BoardController client path /board/list.hanwha");
        
        BoardServiceImpl listBoard() 메서드 호출하여 결과를 반환 받고
        반환받은 결과를 Model(requestScope) 심고 이 데이터를
        forward 되는 페이지에서 출력  
        
        List<BoardResponse> list = service.listBoard() ;  
        for(BoardResponse response : list) {
            System.out.println(response); 
        }        
        model.addAttribute("lst", list ) ;
        return "list" ; 
    }
    */
    @RequestMapping("/list.hanwha") // http:// serverip : port / board / list.hanwha
    public String list(@ModelAttribute("params") PageDTO params, Model model) {
        System.out.println("debug BoardController client path /board/list.hanwha params , " + params);
        // 반환결과만 수정
        PageResponse<BoardResponse> list = service.listBoard(params) ; 
        model.addAttribute("lst", list ) ;
        // 페이징처리를 위해서 list -> listPage 
        // return "list" ; 
        return "listPage" ; 
    }

    // @GetMapping("/view.hanwha/{idx}")
    // public String view(@PathVariable("idx") String idx) {    
    /* 
    @GetMapping("/view.hanwha")
    public String view(BoardRequest params, Model model) {
        System.out.println("debug BoardController client path /board/view.hanwha");
        System.out.println("params value  , " + params.getIdx());
        
        view로부터 키(idx) 값을 전달받고 객체로 바인딩되서 service 에 전달
        response 객체를 반환받고 
        해당 response 객체 Model에 심어서 View 페이지로 전달 과정 
        Optional<BoardResponse> response = service.findBoard(params);
        model.addAttribute("response", response.get() ) ; 

        return "view" ; 
    }
    */

    // 조회수 중복방지 구현으로 커스터마이징......
    // 쿠기를 이용한 WEB : request.getSession() ,  request.getCookies() ; 
    // setMaxAge(60 * 60 * 24 * 30)
    @GetMapping("/view.hanwha")
    public String view( BoardRequest params, 
                        Model model, 
                        HttpServletRequest  request , 
                        HttpServletResponse response) {
        System.out.println("debug BoardController client path /board/view.hanwha");
        System.out.println("params value  , " + params.getIdx());
        
        Cookie [] cookies = request.getCookies() ; 
        System.out.println("debug >>> cookies length : " + cookies.length );  
        int visited = 0 ; 

        for(Cookie cookie : cookies) {
            System.out.println("debug >>>> cookie name , " + cookie.getName() ); 
            if( cookie.getName().equals("visit") ) {
                visited = 1 ;
                System.out.println("debug >>>> cookie exits visited "); 
                if( cookie.getValue().contains(params.getIdx()+"")) {
                    System.out.println("debug >>>> cookie value idx , " + params.getIdx());
                    Optional<BoardResponse> result = service.findBoardNotView(params);
                    model.addAttribute("response", result.get());
                } else {
                    System.out.println("visit cookie exits but params idx not exits"); 
                    cookie.setValue(params.getIdx()+"");
                    response.addCookie(cookie);
                    Optional<BoardResponse> result = service.findBoard(params);
                    model.addAttribute("response", result.get());        
                }
                
            }
        }
        System.out.println("debug >>> after loop visited , " + visited ); 
        if(visited == 0) {
            Cookie c = new Cookie("visit", params.getIdx()+"") ; 
            response.addCookie(c); 
            Optional<BoardResponse> result = service.findBoard(params);
            model.addAttribute("response", result.get());
        }

        return "view" ; 
    }

    @GetMapping("/write.hanwha")
    public String writeForm(BoardRequest params, Model model) {
        System.out.println("debug BoardController client path GET /board/write.hanwha");
        System.out.println("debug >>>>> " + params);
        if( params.getIdx() != null ) {
            Optional<BoardResponse> response = service.findBoard(params);
            System.out.println("debug >>>> update , " +response );
            
            model.addAttribute("response", response) ; 
        } 
        return "write" ; 
    }
    @PostMapping("/write.hanwha")
    public String write(BoardRequest params) {
        System.out.println("debug BoardController client path POST /board/write.hanwha");
        System.out.println("debug >>> params value " + params); 
        
        /*
        params 로 넘겨받은 데이터를 service에게 전달하여 Mapper (save) 
        입력된 데이터의 기본키값을 반환 받고 출력
        화면이동 : list  
        */
        Integer idx = service.saveBoard(params);
        System.out.println("debug result => " + idx + "번 게시글 입력") ; 
        return "redirect:/board/list.hanwha" ;  
        
    }

    @GetMapping("/delete.hanwha")
    public String delete(BoardRequest params) {
        System.out.println("debug BoardController client path GET /board/delete.hanwha");
        System.out.println("debug >>> params value " + params); 
        Integer idx = service.deleteBoard(params);
        System.out.println("debug result => " + idx + "번 게시글 삭제") ; 
        return "redirect:/board/list.hanwha" ;
    }

    @PostMapping("/update.hanwha")
    public String update(BoardRequest params) {
        System.out.println("debug BoardController client path POST /board/update.hanwha");
        System.out.println("debug >>> params value " + params); 
        Integer idx = service.updateBoard(params);
        System.out.println("debug result => " + idx + "번 게시글 수정") ; 
        return "redirect:/list.hanwha" ;  
    }
        
}


