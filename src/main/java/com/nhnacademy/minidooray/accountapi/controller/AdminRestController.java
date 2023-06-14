package com.nhnacademy.minidooray.accountapi.controller;

import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accountapi/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final AdminService adminService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return new ResponseEntity<>(adminService.getAccounts(), HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String id) {
        return new ResponseEntity<>(adminService.getAccount(id), HttpStatus.OK);
    }

    @PatchMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> modifyAccountStatusForAdmin(@PathVariable String id, @RequestBody Map<String, Integer> map) {
        AccountDto accountDto = adminService.modifyAccountStatusForAdmin(id, map.get("statusCode"));
        return ResponseEntity.ok().body(accountDto);
    }

}
