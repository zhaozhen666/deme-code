package com.zhao.ad.sponsor.service.impl;

import com.zhao.ad.sponsor.dao.CreativeRepository;
import com.zhao.ad.sponsor.entity.Creative;
import com.zhao.ad.sponsor.service.ICreativeService;
import com.zhao.ad.sponsor.vo.CreativeRequest;
import com.zhao.ad.sponsor.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Qinyi.
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
