package com.example.encore_spring_pjt.ctrl.board.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Pagination {
    
    // 전체 데이터 수 
    private int totalRecordCnt ; 
    // 전체 페이지 수 
    private int totalPageCnt ; 
    // 첫, 마지막 페이지 번호
    private int startPage  ; 
    private int endPage ;
    // limit offset 위치 번호
    private int limitStart ; 
    // 이전페이지, 다음페이지 존재하는지 여부
    private boolean existRrevPage ;
    private boolean existNextPage ; 

    public Pagination() {
    }
    public Pagination(int totalRecordCnt , PageDTO params) {
        if(totalRecordCnt > 0 ) {
            this.totalRecordCnt = totalRecordCnt ; 
            calc(params);    
        }
    }
    public void calc(PageDTO params) {
        // 전체 페이지 수 
        this.totalPageCnt = ((totalRecordCnt-1) / params.getRecordSize()) + 1 ;         

        // 현재페이지번호가 전체 페이지 수보다 큰 경우
        // 현재페이지에 전체 페이지 수를 저장
        if( params.getPage() > totalPageCnt ) {
            params.setPage(totalPageCnt);
        }
        // 첫 페이지 번호 계산(pagination위한 첫페이지 번호1)
        startPage = ((params.getPage() - 1) / params.getPageSize()) 
                    * params.getPageSize(); 
        
        // 마지막 페이지 번호 계산(pagination위한 마지막페이지 번호10) 
        endPage = startPage + params.getPageSize() - 1 ; 

        // 마지막페이지번호가 전체 페이지 수보다 큰 경우
        // 마지막페이지에 전체 페이지 수를 저장
        if( endPage > totalPageCnt ) {
            endPage = totalPageCnt ;
        }
        
        // limit 시작 위치 
        limitStart = (params.getPage() -1 ) * params.getRecordSize() ; 

        // 이전페이지 존재여부
        existRrevPage = (startPage != 1) ; 

        // 다음페이지 존재여부 
        existNextPage = (endPage * params.getRecordSize()) < totalRecordCnt ; 
    }
}


