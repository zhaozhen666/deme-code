package com.zhao.mvcdemo.service.impl;

import com.zhao.mvcdemo.service.DemoService;
import com.zhao.mvcframework.annotation.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {
    @Override
    public String getName(String name) {
        return name;
    }
}
