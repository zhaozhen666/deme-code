package com.zhao.ad.sponsor.service;

import com.zhao.ad.common.exception.AdException;
import com.zhao.ad.sponsor.vo.CreateUserRequest;
import com.zhao.ad.sponsor.vo.CreateUserResponse;

public interface IUserService {
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
