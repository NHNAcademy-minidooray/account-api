package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StatusCodeRepositoryTest {

    @Autowired
    StatusCodeRepository statusCodeRepository;

    @Test
    void getReferenceByIdTest() {
        StatusCode statusCode = new StatusCode(10, "상태테스트");
        statusCodeRepository.save(statusCode);

        StatusCode actual = statusCodeRepository.getReferenceById(10);

        assertThat(actual.getSequence()).isEqualTo(statusCode.getSequence());
        assertThat(actual.getName()).isEqualTo(statusCode.getName());
    }
}