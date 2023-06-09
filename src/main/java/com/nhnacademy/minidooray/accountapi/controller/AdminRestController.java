package com.nhnacademy.minidooray.accountapi.controller;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.exception.ValidationFailedException;
import com.nhnacademy.minidooray.accountapi.request.AdminModifyRequest;
import com.nhnacademy.minidooray.accountapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final AccountService accountService;

//    회원리스트 조회
//    GET /admin/accounts

    @GetMapping("/accounts")
    public ResponseEntity<Map<String, Account>> getAccounts() {
        return new ResponseEntity(accountService.getAccounts(), HttpStatus.OK);
    }

//    ID로 회원 조회
//    id가 누구든 모든 정보 조회 가능
//    GET /admin/accounts/{id}

    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

//    해당 ID의 회원 상태 변경
//    PATCH /admin/accounts/{id}
    @PatchMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> modifyForAdmin(@PathVariable String id) {
        AccountDto accountDto = accountService.modifyAccountForAdmin(id);
        return ResponseEntity.ok().body(accountDto);
    }

}
