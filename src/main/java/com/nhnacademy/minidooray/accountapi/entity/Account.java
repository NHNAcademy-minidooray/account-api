package com.nhnacademy.minidooray.accountapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "account_password")
    private String password;

    @Column(name = "account_email")
    private String email;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_created_at")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_code_seq")
    private StatusCode status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_code_seq")
    private AuthorityCode authority;

    public void updateAccountInfo(String password, String email, String name) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public void updateStatusCode(StatusCode status) {
        this.status = status;
    }
}
