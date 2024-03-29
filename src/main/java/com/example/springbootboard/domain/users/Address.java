package com.example.springbootboard.domain.users;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class Address {

    @Column(name = "city")
    private String city;
    private String street;
    private String zipcode;
}
