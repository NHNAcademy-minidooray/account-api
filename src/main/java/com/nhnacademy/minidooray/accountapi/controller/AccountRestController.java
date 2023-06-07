package com.nhnacademy.minidooray.accountapi.controller;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.exception.ValidationFailedException;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import com.nhnacademy.minidooray.accountapi.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Account>> getAccounts() {
        return new ResponseEntity(accountService.getAccounts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountRegisterRequest accountRegisterRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        AccountDto accountDto = accountService.createAccount(accountRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<AccountDto> modifyForUser(@PathVariable String id, @Valid @RequestBody AccountModifyRequest accountModifyRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        AccountDto accountDto =accountService.modifyAccount(id, accountModifyRequest);
        return ResponseEntity.ok().body(accountDto);
    }
}
