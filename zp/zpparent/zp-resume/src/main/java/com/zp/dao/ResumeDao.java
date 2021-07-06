package com.zp.dao;

import com.zp.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeDao extends JpaRepository<Resume,Long> {
}
