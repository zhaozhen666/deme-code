package com.zhao.ad.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {

    private Long userId;
    private  String userName;
    private String token;
    private Date createTime;
    private Date updateTime;
}
