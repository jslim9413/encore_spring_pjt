package com.example.encore_spring_pjt.ctrl.user.service;

import org.springframework.stereotype.Service;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;
import com.example.encore_spring_pjt.ctrl.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserMapper userMapper ; 
    

    public UserResponse loginService(UserRequest params) {
        System.out.println("debug >>>> service loginService ");
        return userMapper.loginRow(params) ; 
    }

    // insert 
    public void registerService(UserRequest params) {
        System.out.println("debug >>>> service registerService ");
        userMapper.registerRow(params); 
    }
}





