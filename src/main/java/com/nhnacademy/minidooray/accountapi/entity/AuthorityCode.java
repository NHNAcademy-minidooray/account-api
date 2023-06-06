package com.nhnacademy.minidooray.accountapi.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "AuthorityCodes")
public class AuthorityCode {

    @Id
    @Column(name = "authority_code_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sequence;

    @Column(name = "authority_code_name")
    private String name;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER)
    private List<Account> accounts;
}
