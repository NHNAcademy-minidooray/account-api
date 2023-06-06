package com.nhnacademy.minidooray.accountapi.repository;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    // Querydsl 작성

//    @Modifying
//    @Query("UPDATE Account a SET a.password = :password, a.email = :email, a."
//    void updatePwdAndEmailAndName()

    Optional<AccountDto> findByAccountId(String id);

    List<AccountDto> findAllBy();
}
