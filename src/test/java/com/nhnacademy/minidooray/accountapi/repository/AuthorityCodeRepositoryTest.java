package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorityCodeRepositoryTest {
    @Autowired
    AuthorityCodeRepository authorityCodeRepository;

    @Test
    void getReferenceByIdTest() {
        AuthorityCode authorityCode = new AuthorityCode(10, "권한테스트");
        authorityCodeRepository.save(authorityCode);

        AuthorityCode actual = authorityCodeRepository.getReferenceById(10);

        assertThat(actual.getSequence()).isEqualTo(authorityCode.getSequence());
        assertThat(actual.getName()).isEqualTo(authorityCode.getName());
    }
}