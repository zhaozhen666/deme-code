package com.zhao.repository;

import com.zhao.entity.CUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CUserRepository extends JpaRepository<CUser,Long> {

    List<CUser> findByPwd(String pwd);
}
