package com.zhao.ad.common.advice;

import com.zhao.ad.common.exception.AdException;
import com.zhao.ad.common.vo.CommonRespone;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonRespone<String> handlerAdException(HttpServletRequest request, AdException e) {
        CommonRespone<String> commonRespone = new CommonRespone<>(-1, "business error");
        commonRespone.setData(e.getMessage());
        return commonRespone;
    }
}
