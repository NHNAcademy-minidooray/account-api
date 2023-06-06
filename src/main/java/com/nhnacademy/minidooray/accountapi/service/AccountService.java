package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;

import java.util.List;

public interface AccountService {
    AccountDto getAccount(String id);

    List<AccountDto> getAccounts();

    AccountDto createAccount(AccountRegisterRequest accountRegisterRequest);

    AccountDto modifyAccount(String id, AccountModifyRequest accountModifyRequest);
}
