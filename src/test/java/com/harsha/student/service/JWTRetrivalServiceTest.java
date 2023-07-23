package com.harsha.student.service;


import com.harsha.student.model.entites.SigninRequest;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class JWTRetrivalServiceTest {

    RestTemplate restTemplate=new RestTemplate();
    JWTRetrivalService jwtRetrivalService=new JWTRetrivalService(restTemplate);

    @Test
    void getToken()
    {
        SigninRequest jwtRetrivalVM= new SigninRequest();
        jwtRetrivalVM.setUsername("harsha");
        jwtRetrivalVM.setPassword("abc");
        final String jwt = jwtRetrivalService.getJWT(jwtRetrivalVM);

        System.out.println("JWT ="+ jwt);
    }


}