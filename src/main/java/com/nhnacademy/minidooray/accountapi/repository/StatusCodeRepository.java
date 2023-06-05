package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusCodeRepository extends JpaRepository<StatusCode, Integer> {
}
