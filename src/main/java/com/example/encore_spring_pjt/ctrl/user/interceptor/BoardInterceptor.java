package com.example.encore_spring_pjt.ctrl.user.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(   HttpServletRequest request, 
                                HttpServletResponse response, 
                                Object handler) throws Exception {
        System.out.println("debug >>> board interceptor preHandle");
        log.debug("debug >>> board interceptor preHandle");

        HttpSession session = request.getSession() ;                             
        if( session.getAttribute("loginUser") == null) {
            System.out.println("debug >>> session exits N"); 
            response.sendRedirect("/") ;  
        }                                

        return true ; 
    }
}

