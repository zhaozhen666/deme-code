package com.zp.feignclient;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFallBack implements ResumeFeignClient {
    @Override
    public Integer findDefaultResumeState(Long userId) {
        return -1;
    }
}
