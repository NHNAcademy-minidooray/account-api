package com.nhnacademy.minidooray.accountapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AuthorityCodes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityCode {

    @Id
    @Column(name = "authority_code_seq")
    private Integer sequence;

    @Column(name = "authority_code_name")
    private String name;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER)
    private List<Account> accounts;

    public AuthorityCode(Integer sequence, String name) {
        this.sequence = sequence;
        this.name = name;
    }
}
