package com.nhnacademy.minidooray.accountapi.service;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;

import java.util.List;

public interface AdminService {
    AccountDto getAccount(String id);
    List<AccountDto> getAccounts();
    AccountDto modifyAccountForAdmin(String id, Integer statusCode);
}
