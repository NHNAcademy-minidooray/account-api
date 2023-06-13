package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AuthorityCodeRepositoryTest {
    @Autowired
    AuthorityCodeRepository authorityCodeRepository;

    @Test
    void getReferenceByIdTest() {
        AuthorityCode authorityCode = new AuthorityCode(10, "권한테스트");
        authorityCodeRepository.save(authorityCode);

        Optional<AuthorityCode> optional = authorityCodeRepository.findById(10);

        assertThat(optional.get().getSequence()).isEqualTo(authorityCode.getSequence());
        assertThat(optional.get().getSequence()).isEqualTo(authorityCode.getSequence());
    }
}