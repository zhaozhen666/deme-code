package com.zp.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "zhao-service-resume", fallback = FeignClientFallBack.class)
public interface ResumeFeignClient {
    @GetMapping("/resume/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId);
}
