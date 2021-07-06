package com.zp.service.impl;

import com.zp.dao.ResumeDao;
import com.zp.pojo.Resume;
import com.zp.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public Resume findDefaultResumeByUserId(Long userId) {
        Resume resume = new Resume();
        resume.setUserId(userId);
        // 查询默认简历
        resume.setIsDefault(1);
        Example<Resume> example = Example.of(resume);
        return resumeDao.findOne(example).get();
    }
}
