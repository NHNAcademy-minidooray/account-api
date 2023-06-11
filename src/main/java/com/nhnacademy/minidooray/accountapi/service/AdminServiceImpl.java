package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.exception.AccountNotFoundException;
import com.nhnacademy.minidooray.accountapi.exception.UnauthorizedAdminException;
import com.nhnacademy.minidooray.accountapi.repository.AccountRepository;
import com.nhnacademy.minidooray.accountapi.repository.StatusCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final AccountRepository accountRepository;
    private final StatusCodeRepository statusCodeRepository;

    @Override
    public AccountDto getAccount(String id) {
        accountRepository.findById(id).orElseThrow(() -> {
            throw new AccountNotFoundException(id);
        });
        return accountRepository.findAccountById(id);
    }

    @Override
    public List<AccountDto> getAccounts() {
        return accountRepository.findAccountAll();
    }

    @Override
    @Transactional
    public AccountDto modifyAccountStatusForAdmin(String id, Integer statusCode) {
        if (statusCode.equals(1) || statusCode.equals(3)) {
            Account account = accountRepository.findById(id).orElseThrow(() -> {
                throw new AccountNotFoundException(id);
            });

            account.updateStatusCode(statusCodeRepository.getReferenceById(statusCode));

            return accountRepository.findAccountById(id);
        } else {
            throw new UnauthorizedAdminException(statusCode);
        }

    }
}
