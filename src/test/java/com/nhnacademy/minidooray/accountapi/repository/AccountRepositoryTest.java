package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AccountRepositoryTest {

    // findById(String id)
    // save(Account account)

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StatusCodeRepository statusCodeRepository;

    @Autowired
    AuthorityCodeRepository authorityCodeRepository;

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

        StatusCode statusCode = new StatusCode(4, "가입테스트");
        statusCodeRepository.save(statusCode);

        AuthorityCode authorityCode = new AuthorityCode(4, "회원테스트");
        authorityCodeRepository.save(authorityCode);

        Account account = new Account("test", "$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK",
                "test@naver.com", "imtest", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
        accountRepository.save(account);

        Optional<StatusCode> actualStatusCode = statusCodeRepository.findById(statusCode.getSequence());
        Optional<AuthorityCode> actualAuthorityCode = authorityCodeRepository.findById(authorityCode.getSequence());
        Optional<Account> actualAccount = accountRepository.findById(account.getAccountId());

        assertThat(actualStatusCode).isEqualTo(statusCodeRepository.findById(4));
        assertThat(actualAuthorityCode).isEqualTo(authorityCodeRepository.findById(4));
        assertThat(actualAccount).isEqualTo(accountRepository.findById("test"));
    }

}