package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AccountRepositoryCustomTest {

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
    void findAccountByIdTest() {

        Account account = new Account("test", "test", "test@naver.com", "imtest",
                LocalDate.now(), testEntityManager.find(StatusCode.class, statusCode.getSequence()), testEntityManager.find(AuthorityCode.class, authorityCode.getSequence()));
        accountRepository.save(account);

        AccountDto accountDto = new AccountDto("test", "test", "test@naver.com", "imtest",
                LocalDate.now(), statusCode.getSequence(), authorityCode.getSequence());

        assertThat(accountDto.getStatusCode()).isEqualTo(accountRepository.findAccountById("test").getStatusCode());
        assertThat(accountDto.getAuthorityCode()).isEqualTo(accountRepository.findAccountById("test").getAuthorityCode());
    }

    @Test
    void findAccountAllTest() {

        // given
        Account account1 = new Account("test1", "test1", "test1@naver.com", "imtest1",
                LocalDate.now(), testEntityManager.find(StatusCode.class, statusCode.getSequence()), testEntityManager.find(AuthorityCode.class, authorityCode.getSequence()));
        accountRepository.save(account1);

        Account account2 = new Account("test2", "test2", "test2@naver.com", "imtest2",
                LocalDate.now(), testEntityManager.find(StatusCode.class, statusCode.getSequence()), testEntityManager.find(AuthorityCode.class, authorityCode.getSequence()));
        accountRepository.save(account2);

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);

        // when
        List<AccountDto> accountDtoList = accountRepository.findAccountAll();

        // then
        assertThat(accountDtoList.size()).isEqualTo(accountList.size());

        int idx = 0;
        while (idx < accountDtoList.size()) {
            assertThat(accountDtoList.get(idx).getStatusCode()).isEqualTo(accountList.get(idx).getStatus().getSequence());
            assertThat(accountDtoList.get(idx).getAuthorityCode()).isEqualTo(accountList.get(idx).getAuthority().getSequence());
            idx++;
        }
    }
}