package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AccountRepositoryCustomTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StatusCodeRepository statusCodeRepository;

    @Autowired
    AuthorityCodeRepository authorityCodeRepository;

    @Test
    void findAccountByIdTest() {

        StatusCode statusCode = new StatusCode(4, "상태테스트");
        statusCodeRepository.save(statusCode);

        AuthorityCode authorityCode = new AuthorityCode(4, "권한테스트");
        authorityCodeRepository.save(authorityCode);

        // todo 아래 해시값은 "ksy"를 인코딩한 값 -> "test"를 인코딩한 값 넣어야함
        Account account = new Account("test", "$2a$10$2JrtuV13mUgKMNc6S25HVe0NX.q7vKlUpgQJi6WmYJ/B24XG7lp6S",
                "test@naver.com", "imtest", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
        accountRepository.save(account);

        AccountDto accountDto = new AccountDto("test", "$2a$10$2JrtuV13mUgKMNc6S25HVe0NX.q7vKlUpgQJi6WmYJ/B24XG7lp6S",
                "test@naver.com", "imtest", LocalDate.now(), 4, 4);

        assertThat(accountDto.getStatusCode()).isEqualTo(accountRepository.findAccountById("test").getStatusCode());
        assertThat(accountDto.getAuthorityCode()).isEqualTo(accountRepository.findAccountById("test").getAuthorityCode());
    }

    @Test
    void findAccountAllTest() {

        statusCodeRepository.save(new StatusCode(4, "상태테스트"));
        authorityCodeRepository.save(new AuthorityCode(4, "권한테스트"));

        Account account1 = new Account("test1", "$2a$10$2JrtuV13mUgKMNc6S25HVe0NX.q7vKlUpgQJi6WmYJ/B24XG7lp6S",
                "test1@naver.com", "imtest1", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
        accountRepository.save(account1);

        Account account2 = new Account("test2", "$2a$10$2JrtuV13mUgKMNc6S25HVe0NX.q7vKlUpgQJi6WmYJ/B24XG7lp6S",
                "test2@naver.com", "imtest2", LocalDate.now(), statusCodeRepository.getReferenceById(4), authorityCodeRepository.getReferenceById(4));
        accountRepository.save(account2);

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);

        List<AccountDto> accountDtoList = accountRepository.findAccountAll();

        assertThat(accountDtoList.size()).isEqualTo(accountList.size());

        int idx = 0;
        while (idx < accountDtoList.size()) {
            assertThat(accountDtoList.get(idx).getStatusCode()).isEqualTo(accountList.get(idx).getStatus().getSequence());
            assertThat(accountDtoList.get(idx).getAuthorityCode()).isEqualTo(accountList.get(idx).getAuthority().getSequence());
            idx++;
        }
    }
}