package com.zhao.ad.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonRespone<T> implements Serializable {
    private Integer code;
    private String message;

    private T data;

    public CommonRespone(Integer code ,String message){
        this.code=code;
        this.message = message;
    }
}
