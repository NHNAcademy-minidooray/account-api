package com.nhnacademy.minidooray.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.service.AdminService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminRestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private AdminService adminService;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void getAccounts() throws Exception {
        List<AccountDto> accountDtoList = new ArrayList<>();

        AccountDto accountDto1 = AccountDto.builder()
                .accountId("test1")
                .password("test1")
                .email("test1@naver.com")
                .name("test1")
                .createdAt(LocalDate.now())
                .statusCode(1)
                .authorityCode(2)
                .build();

        AccountDto accountDto2 = AccountDto.builder()
                .accountId("test2")
                .password("test2")
                .email("test2@naver.com")
                .name("test2")
                .createdAt(LocalDate.now())
                .statusCode(1)
                .authorityCode(2)
                .build();

        accountDtoList.add(accountDto1);
        accountDtoList.add(accountDto2);

        when(adminService.getAccounts()).thenReturn(accountDtoList);

        List<AccountDto> actual = adminService.getAccounts();

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/accounts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("size()", equalTo(actual.size())))
                .andDo(print());
    }

    @Test
    void getAccount() throws Exception {
        AccountDto accountDto = AccountDto.builder()
                .accountId("test")
                .password("test")
                .name("imtest")
                .email("test@naver.com")
                .createdAt(LocalDate.now())
                .statusCode(1)
                .authorityCode(1)
                .build();

        when(adminService.getAccount(anyString())).thenReturn(accountDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/accounts/{id}", "test")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountDto.getAccountId()))
                .andExpect(jsonPath("$.password").value(accountDto.getPassword()))
                .andExpect(jsonPath("$.name").value(accountDto.getName()))
                .andExpect(jsonPath("$.email").value(accountDto.getEmail()))
                .andExpect(jsonPath("$.createdAt").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.statusCode").value(1))
                .andExpect(jsonPath("$.authorityCode").value(1))
                .andDo(print());
    }

    @Test
    void modifyAccountStatusForAdmin() throws Exception{
        AccountDto accountDto = AccountDto.builder()
                .accountId("test")
                .statusCode(1)
                .build();

        Map<String, Integer> request = new HashMap<>();
        request.put("statusCode", 3);

        when(adminService.modifyAccountStatusForAdmin(anyString(), anyInt())).thenReturn(accountDto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/admin/accounts/{id}", "test")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountDto.getAccountId()))
                .andExpect(jsonPath("$.statusCode").value(accountDto.getStatusCode()))
                .andDo(print());
    }
}