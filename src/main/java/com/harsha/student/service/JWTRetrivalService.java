package com.harsha.student.service;

import com.harsha.student.model.JwtResponse;

import com.harsha.student.model.entites.SigninRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class JWTRetrivalService {


    private final RestTemplate restTemplate;

    public JWTRetrivalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String ADDRESS_URL="http://localhost:8080/login";
    public String getJWT(SigninRequest jwtRetrivalVM){

        ResponseEntity<JwtResponse> response = restTemplate.postForEntity(ADDRESS_URL, jwtRetrivalVM,JwtResponse.class);
        final String body = response.getBody().getJwt();
        return body;
    }

}
