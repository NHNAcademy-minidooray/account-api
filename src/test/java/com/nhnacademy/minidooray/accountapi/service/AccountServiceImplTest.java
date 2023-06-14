package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.AuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import com.nhnacademy.minidooray.accountapi.exception.AccountExistsException;
import com.nhnacademy.minidooray.accountapi.exception.AccountNotFoundException;
import com.nhnacademy.minidooray.accountapi.repository.AccountRepository;
import com.nhnacademy.minidooray.accountapi.repository.AuthorityCodeRepository;
import com.nhnacademy.minidooray.accountapi.repository.StatusCodeRepository;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private StatusCodeRepository statusCodeRepository;
    @Mock
    private AuthorityCodeRepository authorityCodeRepository;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void getAccountExceptionTest() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class,
                () -> accountService.getAccount("test"));
    }

    @Test
    void getAccountTest() {
        when(accountRepository.findById("test"))
                .thenReturn(Optional.of(mock(Account.class)));
        when(accountRepository.findAccountById(any()))
                .thenReturn(mock(AccountDto.class));

        AccountDto actual = accountService.getAccount("test");
        assertThat(actual)
                .isInstanceOf(AccountDto.class);
    }

    @Test
    void modifyAccountStatusForAccountExceptionTest() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class,
                () -> accountService.modifyAccountStatusForAccount("test"));
    }

    @Test
    void modifyAccountStatusForAccountTest() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.of(mock(Account.class)));

        when(accountRepository.findAccountById(any()))
                .thenReturn(mock(AccountDto.class));

        AccountDto actual = accountService.modifyAccountStatusForAccount("test");
        assertThat(actual)
                .isInstanceOf(AccountDto.class);
    }

    @Test
    void createAccountExceptionTest() {
        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .accountId("test")
                .password("test")
                .email("test@naver.com")
                .name("test")
                .build();

        when(accountRepository.existsById(any()))
                .thenReturn(true);

        Assertions.assertThrows(AccountExistsException.class,
                () -> accountService.createAccount(request));
    }

    @Test
//    @Order(1)
    void createAccountTest() {
        // given
        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .accountId("test")
                .password("test")
                .email("test@naver.com")
                .name("test")
                .build();

        // when
        when(accountRepository.existsById(any()))
                .thenReturn(false);
        when(statusCodeRepository.getReferenceById(any()))
                .thenReturn(mock(StatusCode.class));
        when(authorityCodeRepository.getReferenceById(any()))
                .thenReturn(mock(AuthorityCode.class));

        // then
        accountService.createAccount(request);
        verify(accountRepository, times(1)).findAccountById(any());
    }

    @Test
    void modifyAccountInfoForeAccountExceptionTest() {
        AccountModifyRequest request = AccountModifyRequest.builder()
                .password("test")
                .email("test@naver.com")
                .name("test")
                .build();

        when(accountRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class,
                () -> accountService.modifyAccountInfoForAccount("test", request));
    }

    @Test
    void modifyAccountInfoForAccountTest() {
        // given
        AccountModifyRequest request = AccountModifyRequest.builder()
                .password("test")
                .email("test@naver.com")
                .name("test")
                .build();

        // when
        when(accountRepository.findById(any()))
                .thenReturn(Optional.of(mock(Account.class)));
        when(accountRepository.findAccountById(any()))
                .thenReturn(mock(AccountDto.class));

        // then
        AccountDto actual = accountService.modifyAccountInfoForAccount("test", request);
        assertThat(actual)
                .isInstanceOf(AccountDto.class);
    }
}