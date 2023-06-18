package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.entity.QAccount;
import com.nhnacademy.minidooray.accountapi.entity.QAuthorityCode;
import com.nhnacademy.minidooray.accountapi.entity.QStatusCode;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class AccountRepositoryImpl extends QuerydslRepositorySupport implements AccountRepositoryCustom {
    public AccountRepositoryImpl() {
        super(Account.class);
    }


    @Override
    public AccountDto findAccountById(String accountId) {
        QAccount account = QAccount.account;
        QStatusCode statusCode = QStatusCode.statusCode;
        QAuthorityCode authorityCode = QAuthorityCode.authorityCode;

        return from(account)
                .innerJoin(account.authority, authorityCode)
                .innerJoin(account.status, statusCode)
                .where(account.accountId.eq(accountId))
                .select(Projections.constructor(AccountDto.class, account.accountId, account.password,
                        account.email, account.name, account.createdAt, statusCode.sequence, authorityCode.sequence))
                .fetch().stream().findFirst().orElseThrow();
    }

    @Override
    public List<AccountDto> findAccountAll() {
        QAccount account = QAccount.account;
        QStatusCode statusCode = QStatusCode.statusCode;
        QAuthorityCode authorityCode = QAuthorityCode.authorityCode;

        return from(account)
                .innerJoin(account.authority, authorityCode)
                .innerJoin(account.status, statusCode)
                .select(Projections.constructor(AccountDto.class, account.accountId, account.password,
                        account.email, account.name, account.createdAt, statusCode.sequence, authorityCode.sequence))
                .fetchResults().getResults();
    }

    @Override
    public List<AccountDto> findAccountExceptMe(String accountId) {
        QAccount account = QAccount.account;
        QStatusCode statusCode = QStatusCode.statusCode;
        QAuthorityCode authorityCode = QAuthorityCode.authorityCode;

        return from(account)
                .innerJoin(account.authority, authorityCode)
                .innerJoin(account.status, statusCode)
                .where(account.accountId.ne(accountId))
                .select(Projections.constructor(AccountDto.class, account.accountId, account.password,
                        account.email, account.name, account.createdAt, statusCode.sequence, authorityCode.sequence))
                .fetchResults().getResults();
    }
}
