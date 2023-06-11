package com.nhnacademy.minidooray.accountapi.controller;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.entity.Account;
import com.nhnacademy.minidooray.accountapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final AdminService adminService;

//    회원리스트 조회
//    GET /admin/accounts

    @GetMapping("/accounts")
    public ResponseEntity<Map<String, Account>> getAccounts() {
        return new ResponseEntity(adminService.getAccounts(), HttpStatus.OK);
    }

//    ID로 회원 조회
//    id가 누구든 모든 정보 조회 가능
//    GET /admin/accounts/{id}

    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        return new ResponseEntity<>(adminService.getAccount(id), HttpStatus.OK);
    }

//    해당 ID의 회원 상태 변경 가입 <-> 휴면
//    PATCH /admin/accounts/{id}
    @PatchMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> modifyAccountStatusForAdmin(@PathVariable String id, @RequestBody Map<String, Integer> map) {
        AccountDto accountDto = adminService.modifyAccountStatusForAdmin(id, map.get("statusCode"));
        return ResponseEntity.ok().body(accountDto);
    }

}
