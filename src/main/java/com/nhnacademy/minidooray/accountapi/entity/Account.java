package com.nhnacademy.minidooray.accountapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "account_password")
    private String password;

    @Column(name = "account_email")
    private String email;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_code_seq")
    private StatusCode status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_code_seq")
    private AuthorityCode authority;
}
