package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import com.nhnacademy.minidooray.accountapi.exception.AccountNotFoundException;
import com.nhnacademy.minidooray.accountapi.exception.UnauthorizedAdminException;
import com.nhnacademy.minidooray.accountapi.repository.AccountRepository;
import com.nhnacademy.minidooray.accountapi.repository.StatusCodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @InjectMocks
    AdminServiceImpl adminService;
    @Mock
    AccountRepository accountRepository;
    @Mock
    StatusCodeRepository statusCodeRepository;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void getAccountExceptionTest() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> adminService.getAccount("test"));
    }

    @Test
    void getAccountTest() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.of(mock(Account.class)));
        when(accountRepository.findAccountById(any()))
                .thenReturn(mock(AccountDto.class));

        AccountDto actual = adminService.getAccount("test");
        assertThat(actual)
                .isInstanceOf(AccountDto.class);
    }

    @Test
    void getAccountsTest() {
        List<AccountDto> testList = List.of(mock(AccountDto.class));

        when(accountRepository.findAccountAll())
                .thenReturn(testList);

        List<AccountDto> actual = adminService.getAccounts();
        assertThat(actual.size())
                .isEqualTo(testList.size());
    }

    @Test
    void modifyAccountStatusForAdminUnauthorizedTest() {
        assertThrows(UnauthorizedAdminException.class,
                () -> adminService.modifyAccountStatusForAdmin("test", 2));
    }

    @Test
    void modifyAccountStatusForAdminNotFoundExceptionTest() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> adminService.modifyAccountStatusForAdmin("test", 1));
    }

    @Test
    void modifyAccountStatusForAdminTest() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.of(mock(Account.class)));
        when(statusCodeRepository.getReferenceById(1))
                .thenReturn(mock(StatusCode.class));
        when(accountRepository.findAccountById(any()))
                .thenReturn(mock(AccountDto.class));

        AccountDto actual = adminService.modifyAccountStatusForAdmin("test", 1);
        assertThat(actual)
                .isInstanceOf(AccountDto.class);
    }
}