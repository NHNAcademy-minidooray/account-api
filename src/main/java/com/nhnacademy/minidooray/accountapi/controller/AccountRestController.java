package com.nhnacademy.minidooray.accountapi.controller;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
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

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    // ID로 회원 조회
    // 본인 아이디일때만 본인 정보 조회 가능(로그인용)
    // GET /accounts/{id}

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    // ### 본인 상태변경 가입 -> 탈퇴
    // GET /account/withdraw/{id}
    @GetMapping(value = "/withdraw/{id}")
    public ResponseEntity<AccountDto> modifyAccountStatusForAccount(@PathVariable String id) {
        AccountDto accountDto = accountService.modifyAccountStatusForAccount(id);
        return ResponseEntity.ok().body(accountDto);
    }

    // 회원 가입
    // POST /accounts

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountRegisterRequest accountRegisterRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        AccountDto accountDto = accountService.createAccount(accountRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    // 회원이 정보 수정
    // password, email, name 만 수정 가능
    // PATCH /accounts/{id}

    @PatchMapping(value = "/{id}")
    public ResponseEntity<AccountDto> modifyAccountInfoForAccount(@PathVariable String id, @Valid @RequestBody AccountModifyRequest accountModifyRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        AccountDto accountDto = accountService.modifyAccountInfoForAccount(id, accountModifyRequest);
        return ResponseEntity.ok().body(accountDto);
    }
}
