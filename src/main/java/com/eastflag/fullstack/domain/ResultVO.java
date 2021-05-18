package com.eastflag.fullstack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //모든 프라퍼티를 포함한 생성자를 생성해준다.
public class ResultVO { //insert나 update시 쿼리 결과만 리턴할 필요가 있음.
    private int code;
    private String message;
}