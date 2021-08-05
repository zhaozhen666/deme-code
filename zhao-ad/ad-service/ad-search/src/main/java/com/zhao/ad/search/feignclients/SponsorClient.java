package com.zhao.ad.search.feignclients;

import com.zhao.ad.common.vo.CommonRespone;
import com.zhao.ad.search.entity.AdPlan;
import com.zhao.ad.search.vo.AdPlanGetRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class)
public interface SponsorClient {
    @RequestMapping(value = "/ad-sponsor/get/adPlan", method = RequestMethod.POST)
    public CommonRespone<List<AdPlan>> getAdplanByFeign(@RequestBody AdPlanGetRequest request);
}
