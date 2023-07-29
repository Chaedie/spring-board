package com.example.springbootboard.domain.users;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Account {
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private Bank bank;
}
