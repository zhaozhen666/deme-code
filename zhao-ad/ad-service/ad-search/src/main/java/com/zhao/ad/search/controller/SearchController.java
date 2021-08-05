package com.zhao.ad.search.controller;

import com.alibaba.fastjson.JSON;
import com.zhao.ad.common.annotation.IgnoreCommonResponse;
import com.zhao.ad.common.vo.CommonRespone;
import com.zhao.ad.search.entity.AdPlan;
import com.zhao.ad.search.feignclients.SponsorClient;
import com.zhao.ad.search.vo.AdPlanGetRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class SearchController {
    private final RestTemplate restTemplate;

    private SponsorClient sponsorClient;

    @Autowired
    public SearchController(RestTemplate restTemplate, SponsorClient sponsorClient) {
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
    }

    @PostMapping("/getAdPlanByFeign")
    @IgnoreCommonResponse
    @SuppressWarnings("all")
    public CommonRespone<List<AdPlan>> getAdplanByFeign(@RequestBody AdPlanGetRequest request) {

        log.info("ad-search getAdPlanByFeign" + JSON.toJSONString(request));
        return sponsorClient.getAdplanByFeign(request);
    }

    @PostMapping("/getAdPlanByRibbon")
    @IgnoreCommonResponse
    @SuppressWarnings("all")
    public CommonRespone<List<AdPlan>> getAdplanByRibbon(@RequestBody AdPlanGetRequest request) {

        log.info("ad-search getAdPlanByRibbon" + JSON.toJSONString(request));
        return restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan", request, CommonRespone.class).getBody();
    }
}
