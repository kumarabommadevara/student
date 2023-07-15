package com.harsha.student.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
    private String city;
    private String streetName;
    private Integer zipCode;
    private String state;
    private String country;
}
