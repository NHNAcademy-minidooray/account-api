package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TestEntityManager testEntityManager;

    StatusCode statusCode;
    AuthorityCode authorityCode;

    @BeforeEach
    void setUp() {
        statusCode = new StatusCode(4, "상태테스트");
        authorityCode = new AuthorityCode(4, "권한테스트");

        testEntityManager.persist(statusCode);
        testEntityManager.persist(authorityCode);
    }

    @Test
    void findByIdTest() throws Exception {

        String id = "kimsiyeon";
        Optional<Account> actual = accountRepository.findById(id);
        assertThat(actual.isPresent());

        id = "test";
        actual = accountRepository.findById(id);
        assertThat(actual.isEmpty());
    }

    @Test
    void saveTest() throws Exception {

        Account account = new Account("test", "test", "test@naver.com", "imtest",
                LocalDate.now(), testEntityManager.find(statusCode.getClass(), statusCode.getSequence()), testEntityManager.find(authorityCode.getClass(), authorityCode.getSequence()));
        accountRepository.save(account);

        StatusCode actualStatusCode = testEntityManager.find(statusCode.getClass(), statusCode.getSequence());
        AuthorityCode actualAuthorityCode = testEntityManager.find(authorityCode.getClass(), actualStatusCode.getSequence());
        Optional<Account> actualAccount = accountRepository.findById(account.getAccountId());

        assertThat(actualStatusCode).isEqualTo(testEntityManager.find(statusCode.getClass(), statusCode.getSequence()));
        assertThat(actualAuthorityCode).isEqualTo(testEntityManager.find(authorityCode.getClass(), authorityCode.getSequence()));
        assertThat(actualAccount).isEqualTo(accountRepository.findById("test"));
    }

}