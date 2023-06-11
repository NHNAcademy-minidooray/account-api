package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;


public interface AccountService {
    AccountDto getAccount(String id);

    AccountDto createAccount(AccountRegisterRequest accountRegisterRequest);

    AccountDto modifyAccountInfoForAccount(String id, AccountModifyRequest accountModifyRequest);
    AccountDto modifyAccountStatusForAccount(String id);
}
