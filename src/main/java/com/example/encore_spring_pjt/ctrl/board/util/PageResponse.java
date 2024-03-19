package com.example.encore_spring_pjt.ctrl.board.util;

import java.util.List ;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList ;

@Getter
@Setter
// List<BoardResponse> + Pagination 정보를 담는 객체로 역할
public class PageResponse<T> {
    private List<T> list = new ArrayList<>(); // getList() - list , setList() - list
    private Pagination pagination ; 

    public PageResponse(List<T> list , Pagination pagination) {
        this.list.addAll(list); 
        this.pagination = pagination ;
    }
}
