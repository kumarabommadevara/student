package com.harsha.student.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String jwt;
    private String username;

private String roles;

    public JwtResponse() {
    }

    public JwtResponse(String jwt, String username, String roles) {
        this.jwt = jwt;
        this.username = username;

        this.roles = roles;
    }
}
