package com.nhnacademy.minidooray.accountapi.controller;

import com.nhnacademy.minidooray.accountapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminRestController {
    private final AccountService accountService;

}
