package com.nhnacademy.minidooray.accountapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "StatusCodes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusCode {

    @Id
    @Column(name = "status_code_seq")
    private Integer sequence;

    @Column(name = "status_code_name")
    private String name;

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private List<Account> accounts;

    public StatusCode(Integer sequence, String name) {
        this.sequence = sequence;
        this.name = name;
    }
}
