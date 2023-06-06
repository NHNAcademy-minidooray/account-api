package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.exception.AccountNotFoundException;
import com.nhnacademy.minidooray.accountapi.repository.AccountRepository;

import com.nhnacademy.minidooray.accountapi.repository.AuthorityCodeRepository;
import com.nhnacademy.minidooray.accountapi.repository.StatusCodeRepository;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final StatusCodeRepository statusCodeRepository;
    private final AuthorityCodeRepository authorityCodeRepository;

    @Override
    public AccountDto getAccount(String id) {
        return accountRepository.findByAccountId(id).orElseThrow(() -> {
            throw new AccountNotFoundException(id);
        });
    }

    @Override
    public List<AccountDto> getAccounts() {
        return accountRepository.findAllBy();
    }

    @Override
    @Transactional
    public AccountDto createAccount(AccountRegisterRequest accountRegisterRequest) {
        Account account = Account.builder()
                .accountId(accountRegisterRequest.getId())
                .password(accountRegisterRequest.getPassword())
                .email(accountRegisterRequest.getEmail())
                .name(accountRegisterRequest.getName())
                .createdAt(LocalDate.now())
                .status(statusCodeRepository.getReferenceById(1))
                .authority(authorityCodeRepository.getReferenceById(2))
                .build();
        accountRepository.save(account);
        return accountRepository.findByAccountId(accountRegisterRequest.getId()).orElseThrow(() -> {
            throw new AccountNotFoundException(accountRegisterRequest.getId());
        });
    }

    @Override
    @Transactional
    public AccountDto modifyAccount(String id, AccountModifyRequest accountModifyRequest) {
        Account account = accountRepository.findById(id).get();
        account.setPassword(accountModifyRequest.getPassword());
        account.setEmail(accountModifyRequest.getEmail());
        account.setName(accountModifyRequest.getName());
        accountRepository.save(account);
        return accountRepository.findByAccountId(account.getAccountId()).orElseThrow(() -> {
            throw new AccountNotFoundException(account.getAccountId());
        });
    }
}
