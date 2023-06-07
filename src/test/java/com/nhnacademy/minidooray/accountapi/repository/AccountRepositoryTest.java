//package com.nhnacademy.minidooray.accountapi.repository;
//
//import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
//import com.nhnacademy.minidooray.accountapi.entity.Account;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
//@DataJpaTest
//class AccountRepositoryTest {
//
//    @Autowired
//    TestEntityManager testEntityManager;
//    @Autowired
//    AccountRepository accountRepository;
//    @Autowired
//    StatusCodeRepository statusCodeRepository;
//    @Autowired
//    AuthorityCodeRepository authorityCodeRepository;
//
//    @Test
//    void findByAccountIdTest() {
//
//        Account account = new Account("test", "test", "test@naver.com", "imtest",
//                LocalDate.now(), statusCodeRepository.getReferenceById(1), authorityCodeRepository.getReferenceById(2));
//
//        testEntityManager.merge(account);
//
//        AccountDto actual = accountRepository.findByAccountId("test").orElse(null);
//
//        assertThat(actual).isEqualTo(account);
////        Account account = Account.builder()
////                .accountId("test")
////                .password("test")
////                .email("test@naver.com")
////                .name("imtest")
////                .createdAt(LocalDate.now())
////                .status(statusCodeRepository.getReferenceById(1))
////                .authority(authorityCodeRepository.getReferenceById(2))
////                .build();
//    }
//
//    @Test
//    void findAllByTest() {
//    }
//}