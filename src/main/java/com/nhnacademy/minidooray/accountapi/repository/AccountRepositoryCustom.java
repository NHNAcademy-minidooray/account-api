package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AccountRepositoryCustom {

    AccountDto findAccountById(String accountId);

    List<AccountDto> findAccountAll();
}
