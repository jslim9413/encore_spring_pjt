package com.example.encore_spring_pjt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
Builder Pattern
StudentRequest
-- String name, address, stuId, common .....
-- StudentRequest(String name, String address, String stuId ...... ) {
new StudentRequest(address, name ....);    
}
*/
@Builder
@Getter
@Setter
@ToString
public class BoardRequest {
    private Integer idx ; 
    private String  title ; 
    private String  content ;
    private String  writer ;
    private Boolean noticeYn ; 
    private Boolean secretYn ; 

}
