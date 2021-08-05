package com.zhao.ad.search.feignclients;

import com.zhao.ad.common.vo.CommonRespone;
import com.zhao.ad.search.entity.AdPlan;
import com.zhao.ad.search.vo.AdPlanGetRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorClientHystrix implements SponsorClient {
    @Override
    public CommonRespone<List<AdPlan>> getAdplanByFeign(AdPlanGetRequest request) {
        return new CommonRespone<>(-1, "feign ad sponsor error");
    }
}
