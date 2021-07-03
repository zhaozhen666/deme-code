package com.zhao.ad.sponsor.service;

import com.zhao.ad.sponsor.vo.CreativeRequest;
import com.zhao.ad.sponsor.vo.CreativeResponse;

/**
 * Created by Qinyi.
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
