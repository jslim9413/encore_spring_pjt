package com.example.encore_spring_pjt.ctrl.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.encore_spring_pjt.ctrl.user.domain.UserRequest;
import com.example.encore_spring_pjt.ctrl.user.domain.UserResponse;

@Mapper
public interface UserMapper {
    
    public UserResponse loginRow(UserRequest params)   ;  
    public void         registerRow(UserRequest params);
     
}

