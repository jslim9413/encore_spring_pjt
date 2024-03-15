package com.example.encore_spring_pjt.ctrl.user;

import java.util.List;
import java.util.Map;
import java.util.HashMap ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
import com.example.encore_spring_pjt.ctrl.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




@Controller
@RequestMapping("/user") // http:// server ip  : port /user
@RequiredArgsConstructor
public class UserController {

    private final UserService userService ; 
    private final PasswordEncoder passwordEncoder ; 

    @PostMapping("/login.hanwha") // http:// server ip  : port /user/login.hanwha
    public String login(UserRequest params, HttpSession session, RedirectAttributes attr) {
        System.out.println("debug UserController client path /user/login.hanwha");
        System.out.println("param value >>> " + params.getId());
        System.out.println("param value >>> " + params.getPwd());  
        
        UserResponse response = userService.loginService(params);
        System.out.println("debug >>> ctrl result , " + response); 

        if(response != null) {
            // 암호화이후 로그인 처리 구현부
            String userPwd = params.getPwd();
            String encorePwd = response.getPwd();
            // 비밀번호 일치 여부를 matches() 함수를 이용해서 확인 
            if(passwordEncoder.matches(userPwd, encorePwd)) {
                System.out.println("debug >>> matches() true") ;
                response.setPwd("");  
                session.setAttribute("loginUser", response);
                return "redirect:/board/list.hanwha" ; 
            }else {
                System.out.println("debug >>> 아이디일치 했지만 패스워드가 다른경우");
                attr.addFlashAttribute("failMsg" ,"비밀번호를 잘못 입력했습니다.\r\n" + //
                                "입력하신 내용을 다시 확인해주세요.");

                return "redirect:/";
            }
            
        }else {
            attr.addFlashAttribute("failMsg" , 
                                "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.\r\n" + //
                                "입력하신 내용을 다시 확인해주세요.");

            return "redirect:/" ; 
        }
        
    }

    @GetMapping("/logout.hanwha")
    public String logout(HttpSession session) {
        System.out.println("debug UserController client path /user/logout.hanwha");
        // session.invalidate() ; 
        // return "redirect:/" ;  
        return null  ;

    }
    @GetMapping("/join.hanwha")
    public String joinForm() {
        System.out.println("debug UserController client GET path /user/join.hanwha");
        return "join" ; 
    }

    @PostMapping("/join.hanwha")
    public String join(@Valid UserRequest params, BindingResult bindingResult, Model model) {
        System.out.println("debug UserController client POST path /user/join.hanwha");
        /*
         * 실습구현
         * step01) 유효성 체크 검증을 통과하지 못하면 에러메시지를 join.jsp 보내서 출력
         * step02) 유효성 체크 검증을 통과하면 service registr() ;
         * step03) UserMapper - insertRow() - UserMapper.xml<insert id="insertRow">
         * step04) 화면분기는 /
         */
        if( bindingResult.hasErrors() ) {
            System.out.println("debug >>> hasErrors") ;  
            List<ObjectError> list = bindingResult.getAllErrors();
            Map<String, String> map = new HashMap<>(); 
            for(int idx = 0 ; idx < list.size() ; idx++) {
                FieldError field = (FieldError)list.get(idx);
                String     msg   = field.getDefaultMessage();
                System.out.println("debug>>>>>>>>>>>>>>>>>");
                System.out.println(field.getField() + "\t" + msg); 
                System.out.println("debug>>>>>>>>>>>>>>>>>");
                model.addAttribute(field.getField(), msg);
            }
            return "join" ; 
        } else {
            System.out.println("debug >>> 유효성 검증 통과~~~");
            
            // 비밀번호 암호화 
            System.out.println("debug >>> passwordEncoder , " + passwordEncoder);
            String encoderPwd = passwordEncoder.encode(params.getPwd());
            System.out.println("encoderPwd , " + encoderPwd ); 
            params.setPwd(encoderPwd); 
            userService.registerService(params); 
            return "redirect:/" ;
        }
        
    }
    
    

}

