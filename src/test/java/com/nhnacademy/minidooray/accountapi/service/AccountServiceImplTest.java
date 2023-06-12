package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import com.nhnacademy.minidooray.accountapi.repository.AccountRepository;
import com.nhnacademy.minidooray.accountapi.repository.AuthorityCodeRepository;
import com.nhnacademy.minidooray.accountapi.repository.StatusCodeRepository;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceImplTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StatusCodeRepository statusCodeRepository;

    @Autowired
    AuthorityCodeRepository authorityCodeRepository;

    @Test
    void getAccountTest() {

        StatusCode statusCode = new StatusCode(4, "상태테스트");
        statusCodeRepository.save(statusCode);
        AuthorityCode authorityCode = new AuthorityCode(4, "권한테스트");
        authorityCodeRepository.save(authorityCode);

        Account actual = new Account("test", "$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK",
                "test@naver.com", "imtest", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
        accountRepository.save(actual);

        AccountDto actualDto = new AccountDto("test", "$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK",
                "test@naver.com", "imtest", LocalDate.now(), 4, 4);

        assertThat(accountService.getAccount("test").getStatusCode()).isEqualTo(actualDto.getStatusCode());
        assertThat(accountService.getAccount("test").getAuthorityCode()).isEqualTo(actualDto.getAuthorityCode());
    }

    @Test
    void modifyAccountStatusForAccountTest() {

        statusCodeRepository.save(new StatusCode(1, "가입"));
        statusCodeRepository.save(new StatusCode(2, "탈퇴"));
        authorityCodeRepository.save(new AuthorityCode(2, "회원"));

        Account account = Account.builder()
                .accountId("test")
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("imtest")
                .createdAt(LocalDate.now())
                .status(statusCodeRepository.getReferenceById(1))
                .authority(authorityCodeRepository.getReferenceById(2))
                .build();
        accountRepository.save(account);

        AccountDto actual = accountService.modifyAccountStatusForAccount("test");
        assertThat(actual.getStatusCode()).isEqualTo(statusCodeRepository.getReferenceById(2).getSequence());
    }

    @Test
    @Order(1)
    void createAccountTest() {

        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .accountId("test")
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("imtest")
                .build();

        AccountDto response = accountService.createAccount(request);
        assertThat(response.getAccountId()).isEqualTo(accountRepository.findAccountById("test").getAccountId());
    }

    @Test
    void modifyAccountInfoForAccountTest() {

        // given
        statusCodeRepository.save(new StatusCode(1, "가입"));
        authorityCodeRepository.save(new AuthorityCode(2, "회원"));

        Account account = Account.builder()
                .accountId("test")
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("imtest")
                .createdAt(LocalDate.now())
                .status(statusCodeRepository.getReferenceById(1))
                .authority(authorityCodeRepository.getReferenceById(2))
                .build();
        accountRepository.save(account);

        // when
        AccountDto actual = accountService.modifyAccountInfoForAccount(account.getAccountId(), new AccountModifyRequest
                ("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK", "changed@gmail.com", "changedName")
        );

        //then
        assertThat(actual.getPassword()).isEqualTo(accountRepository.findAccountById("test").getPassword());
        assertThat(actual.getEmail()).isEqualTo(accountRepository.findAccountById("test").getEmail());
        assertThat(actual.getName()).isEqualTo(accountRepository.findAccountById("test").getName());
    }
}