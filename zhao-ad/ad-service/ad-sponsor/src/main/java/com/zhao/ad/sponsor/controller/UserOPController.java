package com.zhao.ad.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.zhao.ad.common.exception.AdException;
import com.zhao.ad.sponsor.service.IUserService;
import com.zhao.ad.sponsor.vo.CreateUserRequest;
import com.zhao.ad.sponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserOPController {
    private IUserService userService;
    @Autowired
    public UserOPController(IUserService userService){
        this.userService =userService;
    }
    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody  CreateUserRequest request) throws AdException {
        log.info("ad-sponsor--createuser  "+ JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
