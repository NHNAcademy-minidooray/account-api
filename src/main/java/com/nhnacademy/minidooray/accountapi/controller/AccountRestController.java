package com.nhnacademy.minidooray.accountapi.controller;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import com.nhnacademy.minidooray.accountapi.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    @GetMapping(value = "/accounts")
    public ResponseEntity<Map<String, Account>> getAccounts() {
        return new ResponseEntity(accountService.getAccounts(), HttpStatus.OK);
    }

//    @PostMapping("/accounts")
//    public ResponseEntity<AccountDto> createAccount(AccountRegisterRequest accountRegisterRequest) {
//        return new ResponseEntity<>(accountService.createAccount(accountRegisterRequest), HttpStatus.OK);
//    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRegisterRequest accountRegisterRequest) {
        accountService.createAccount(accountRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> modifyForUser(@PathVariable String id, AccountModifyRequest accountModifyRequest) {
        return new ResponseEntity<>(accountService.modifyAccount(id, accountModifyRequest), HttpStatus.OK);
    }
}
