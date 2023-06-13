package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.exception.AccountExistsException;
import com.nhnacademy.minidooray.accountapi.exception.AccountNotFoundException;
import com.nhnacademy.minidooray.accountapi.repository.AccountRepository;
import com.nhnacademy.minidooray.accountapi.repository.AuthorityCodeRepository;
import com.nhnacademy.minidooray.accountapi.repository.StatusCodeRepository;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final StatusCodeRepository statusCodeRepository;
    private final AuthorityCodeRepository authorityCodeRepository;

    private Account findByIdOrElseThrow(String id) {
        return accountRepository.findById(id).orElseThrow(() -> {
            throw new AccountNotFoundException(id);
        });
    }

    @Override
    public AccountDto getAccount(String id) {
        findByIdOrElseThrow(id);

        return accountRepository.findAccountById(id);
    }

    @Override
    @Transactional
    public AccountDto modifyAccountStatusForAccount(String id) {
        Account account = findByIdOrElseThrow(id);

        account.updateStatusCode(statusCodeRepository.getReferenceById(2));
        return accountRepository.findAccountById(account.getAccountId());
    }

    @Override
    @Transactional
    public AccountDto createAccount(AccountRegisterRequest accountRegisterRequest) {
        if (accountRepository.existsById(accountRegisterRequest.getAccountId())) {
            throw new AccountExistsException(accountRegisterRequest.getAccountId());
        }
        Account account = Account.builder()
                .accountId(accountRegisterRequest.getAccountId())
                .password(accountRegisterRequest.getPassword())
                .email(accountRegisterRequest.getEmail())
                .name(accountRegisterRequest.getName())
                .createdAt(LocalDate.now())
                .status(statusCodeRepository.getReferenceById(1))
                .authority(authorityCodeRepository.getReferenceById(2))
                .build();

        accountRepository.save(account);
        return accountRepository.findAccountById(account.getAccountId());
    }

    @Override
    @Transactional
    public AccountDto modifyAccountInfoForAccount(String id, AccountModifyRequest accountModifyRequest) {
        Account account = findByIdOrElseThrow(id);

        account.updateAccountInfo(accountModifyRequest.getPassword(), accountModifyRequest.getEmail(), accountModifyRequest.getName());

        return accountRepository.findAccountById(id);
    }
}
