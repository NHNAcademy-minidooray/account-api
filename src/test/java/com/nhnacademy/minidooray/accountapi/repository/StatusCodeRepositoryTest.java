package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StatusCodeRepositoryTest {

    @Autowired
    StatusCodeRepository statusCodeRepository;

    @Test
    void getReferenceByIdTest() {
        StatusCode statusCode = new StatusCode(10, "상태테스트");
        statusCodeRepository.save(statusCode);

        Optional<StatusCode> actual = statusCodeRepository.findById(10);

        assertThat(actual.get().getSequence()).isEqualTo(statusCode.getSequence());
        assertThat(actual.get().getSequence()).isEqualTo(statusCode.getSequence());
    }
}