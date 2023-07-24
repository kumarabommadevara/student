package com.harsha.student.service;

import com.harsha.student.model.JwtResponse;

import com.harsha.student.model.entites.SigninRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class JWTRetrivalService {

    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate;

    public JWTRetrivalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getJWT(SigninRequest jwtRetrivalVM){
        final String addressApi = discoveryClient.getInstances("ADDRESS_API").get(0).getUri().toString();
        System.out.println(addressApi);

        ResponseEntity<JwtResponse> response = restTemplate.postForEntity(addressApi+"/login", jwtRetrivalVM,JwtResponse.class);
        final String body = response.getBody().getJwt();
        return body;
    }

}
