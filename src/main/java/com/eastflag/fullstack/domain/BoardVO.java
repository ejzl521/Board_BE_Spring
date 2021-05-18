package com.eastflag.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)//json을 만들때 property가 null이면 만들지 말라는 의미이다.
@Data
public class BoardVO {//board 테이블과 매핑되는 객체
    private Integer id;
    private String title;
    private String content;
    private String created;
    private String updated;
}