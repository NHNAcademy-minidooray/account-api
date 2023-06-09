package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import com.nhnacademy.minidooray.accountapi.request.AdminModifyRequest;

import java.util.List;

public interface AccountService {
    AccountDto getAccount(String id);

    List<AccountDto> getAccounts();

    AccountDto createAccount(AccountRegisterRequest accountRegisterRequest);

    AccountDto modifyAccountForMember(String id, AccountModifyRequest accountModifyRequest);

    AccountDto modifyAccountForAdmin(String id);
}
