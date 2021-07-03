package com.zhao.ad.common.advice;

import com.zhao.ad.common.annotation.IgnoreCommonResponse;
import com.zhao.ad.common.vo.CommonRespone;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreCommonResponse.class))
        {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreCommonResponse.class)){
            return false;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonRespone<Object> respone = new CommonRespone<>(0,"");
        if (null==o) {
            return respone;
        }else if (o instanceof CommonRespone){
            respone= (CommonRespone<Object>) o;
        }else {
            respone.setData(o);
        }
        return respone;
    }
}
