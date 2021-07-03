package com.zhao.ad.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.zhao.ad.common.exception.AdException;
import com.zhao.ad.sponsor.entity.AdPlan;
import com.zhao.ad.sponsor.service.IAdPlanService;
import com.zhao.ad.sponsor.vo.AdPlanGetRequest;
import com.zhao.ad.sponsor.vo.AdPlanRequest;
import com.zhao.ad.sponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AdPlanOPController {
    private IAdPlanService adPlanService;
    @Autowired
    public AdPlanOPController(IAdPlanService adPlanService){
        this.adPlanService = adPlanService;
    }

    @PostMapping("/create/adPlan")
    public AdPlanResponse createPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info(" ad-sponsor create adPlan "+ JSON.toJSONString(request));
        return adPlanService.createAdPlan(request);
    }

    @GetMapping("/get/adPlan")
    public List<AdPlan> getAdPlan(@RequestBody AdPlanGetRequest request) throws AdException{
        log.info(" ad-sponsor get adPlan "+JSON.toJSONString(request));
        return adPlanService.getAdPlanByIds(request);
    }

    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(
            @RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: updateAdPlan  "+
                JSON.toJSONString(request));
        return adPlanService.updateAdPlan(request);
    }

    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(
            @RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: deleteAdPlan  "+
                JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }
}
