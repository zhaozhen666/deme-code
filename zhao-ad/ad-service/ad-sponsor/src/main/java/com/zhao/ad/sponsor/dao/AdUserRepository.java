package com.zhao.ad.sponsor.dao;

import com.zhao.ad.sponsor.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Qinyi.
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * <h2>根据用户名查找用户记录</h2>
     */
    AdUser findByUsername(String username);
}
