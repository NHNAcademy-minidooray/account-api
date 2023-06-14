package com.nhnacademy.minidooray.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray.accountapi.dto.AccountDto;
import com.nhnacademy.minidooray.accountapi.exception.ValidationFailedException;
import com.nhnacademy.minidooray.accountapi.request.AccountModifyRequest;
import com.nhnacademy.minidooray.accountapi.request.AccountRegisterRequest;
import com.nhnacademy.minidooray.accountapi.service.AccountService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountRestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private AccountService accountService;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void getAccountTest() throws Exception {
        // given
        AccountDto accountDto = AccountDto.builder()
                .accountId("test")
                .password("test")
                .name("imtest")
                .email("test@naver.com")
                .createdAt(LocalDate.now())
                .statusCode(1)
                .authorityCode(1)
                .build();

        // when
        when(accountService.getAccount(anyString())).thenReturn(accountDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{id}", "test")
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
    @Order(2)
    void modifyAccountStatusForAccountTest() throws Exception {
        // given
        AccountDto accountDto = AccountDto.builder()
                .accountId("test")
                .statusCode(1)
                .build();

        // when
        when(accountService.modifyAccountStatusForAccount(anyString())).thenReturn(accountDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/withdraw/{id}", "test")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountDto.getAccountId()))
                .andExpect(jsonPath("$.statusCode").value(1))
                .andDo(print());
    }

    @Test
    @Order(3)
    void createAccountAccountIdNullTest() {
        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("test")
                .build();
        when(accountService.createAccount(request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
                () -> accountService.createAccount(request));
    }

    @Test
    @Order(4)
    void createAccountPasswordNullTest() {
        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .accountId("test")
                .email("test@naver.com")
                .name("test")
                .build();
        when(accountService.createAccount(request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
                () -> accountService.createAccount(request));
    }

    @Test
    @Order(5)
    void createAccountPasswordLengthTest() {
        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .accountId("test")
                .password("test")
                .email("test@naver.com")
                .name("test")
                .build();
        when(accountService.createAccount(request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
                () -> accountService.createAccount(request));
    }

    @Test
    @Order(6)
    void createAccountEmailNullTest() {
        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .accountId("test")
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .name("test")
                .build();
        when(accountService.createAccount(request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
                () -> accountService.createAccount(request));
    }

    @Test
    @Order(7)
    void createAccountTest() throws Exception {
        AccountRegisterRequest request = AccountRegisterRequest.builder()
                .accountId("test")
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("test")
                .build();

        AccountDto accountDto = AccountDto.builder()
                .accountId("test")
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("test")
                .createdAt(LocalDate.now())
                .statusCode(1)
                .authorityCode(2)
                .build();

        when(accountService.createAccount(any(AccountRegisterRequest.class))).thenReturn(accountDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").value(accountDto.getAccountId()))
                .andExpect(jsonPath("$.password").value(accountDto.getPassword()))
                .andExpect(jsonPath("$.email").value(accountDto.getEmail()))
                .andExpect(jsonPath("$.name").value(accountDto.getName()))
                .andExpect(jsonPath("$.createdAt").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.statusCode").value(1))
                .andExpect(jsonPath("$.authorityCode").value(2))
                .andDo(print());
    }

    @Test
    @Order(8)
    void modifyAccountInfoForAccountPasswordNullTest() {
        AccountModifyRequest request = AccountModifyRequest.builder()
                .email("test@naver.com")
                .name("test")
                .build();
        when(accountService.modifyAccountInfoForAccount("test", request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
            () -> accountService.modifyAccountInfoForAccount("test", request));
    }

    @Test
    @Order(9)
    void modifyAccountInfoForAccountPasswordLengthTest() {
        AccountModifyRequest request = AccountModifyRequest.builder()
                .password("test")
                .email("test@naver.com")
                .name("test")
                .build();
        when(accountService.modifyAccountInfoForAccount("test", request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
                () -> accountService.modifyAccountInfoForAccount("test", request));

    }

    @Test
    @Order(10)
    void modifyAccountInfoForAccountEmailNullTest() {
        AccountModifyRequest request = AccountModifyRequest.builder()
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .name("test")
                .build();
        when(accountService.modifyAccountInfoForAccount("test", request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
                () -> accountService.modifyAccountInfoForAccount("test", request));
    }

    @Test
    @Order(11)
    void modifyAccountInfoForAccountNameNullTest() {
        AccountModifyRequest request = AccountModifyRequest.builder()
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .build();
        when(accountService.modifyAccountInfoForAccount("test", request)).thenThrow(ValidationFailedException.class);

        Assertions.assertThrows(ValidationFailedException.class,
                () -> accountService.modifyAccountInfoForAccount("test", request));
    }

    @Test
    @Order(12)
    void modifyAccountInfoForAccountTest() throws Exception{
        AccountModifyRequest request = AccountModifyRequest.builder()
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("test")
                .build();

        AccountDto accountDto = AccountDto.builder()
                .accountId("test")
                .password("$2a$10$BScokNq3/NR9fcVi0/I1PuWrYv3rdlqMLxeEk5qxxrToyxmsOYRYK")
                .email("test@naver.com")
                .name("test")
                .build();

        when(accountService.modifyAccountInfoForAccount(anyString(), any(AccountModifyRequest.class))).thenReturn(accountDto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/accounts/{id}", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountDto.getAccountId()))
                .andExpect(jsonPath("$.password").value(accountDto.getPassword()))
                .andExpect(jsonPath("$.email").value(accountDto.getEmail()))
                .andExpect(jsonPath("$.name").value(accountDto.getName()))
            .andDo(print());
    }
}