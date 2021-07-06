package com.zp.service;

import com.zp.pojo.Resume;

public interface ResumeService {

    Resume findDefaultResumeByUserId(Long userId);
}
