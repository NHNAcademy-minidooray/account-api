package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import com.nhnacademy.minidooray.accountapi.repository.AccountRepository;
import com.nhnacademy.minidooray.accountapi.repository.AuthorityCodeRepository;
import com.nhnacademy.minidooray.accountapi.repository.StatusCodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminService adminService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StatusCodeRepository statusCodeRepository;

    @Autowired
    AuthorityCodeRepository authorityCodeRepository;

    @Test
    void getAccountTest() {
        statusCodeRepository.save(new StatusCode(4, "상태테스트"));
        authorityCodeRepository.save(new AuthorityCode(4, "권한테스트"));

        Account actual = new Account("test", "$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK",
                "test@naver.com", "imtest", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
        accountRepository.save(actual);

        AccountDto actualDto = new AccountDto("test", "$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK",
                "test@naver.com", "imtest", LocalDate.now(), 4, 4);

        assertThat(adminService.getAccount(actual.getAccountId()).getStatusCode()).isEqualTo(actualDto.getStatusCode());
        assertThat(adminService.getAccount(actual.getAccountId()).getAuthorityCode()).isEqualTo(actualDto.getAuthorityCode());
    }

    @Test
    void getAccountsTest() {
//        statusCodeRepository.save(new StatusCode(4, "상태테스트"));
//        authorityCodeRepository.save(new AuthorityCode(4, "권한테스트"));
//
//        Account account1 = new Account("test1", "$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK",
//                "test1@naver.com", "imtest1", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
//        accountRepository.save(account1);
//
//        Account account2 = new Account("test2", "$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK",
//                "test2@naver.com", "imtest2", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
//        accountRepository.save(account2);
//
//
//        List<Account> accountList = new ArrayList<>();
//        accountList.add(account1);
//        accountList.add(account2);
//
//        List<AccountDto> accountDtoList = adminService.getAccounts();
//
//        assertThat(accountDtoList.size()).isEqualTo(accountList.size());
//
//        int idx = 0;
//        while (idx < accountDtoList.size()) {
//            assertThat(accountDtoList.get(idx).getStatusCode()).isEqualTo(accountList.get(idx).getStatus().getSequence());
//            assertThat(accountDtoList.get(idx).getAuthorityCode()).isEqualTo(accountList.get(idx).getAuthority().getSequence());
//            idx++;
//        }
    }

    @Test
    void modifyAccountStatusForAdminTest() {
    }
}