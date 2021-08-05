package com.zhao.rpc;


public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        System.err.println("---------服务调用-------------");
        return "hello! " + name;
    }

    @Override
    public String hello(User user) {
        return "hello! " + user.getName();
    }

}
