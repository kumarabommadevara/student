package com.harsha.student.service;

import com.harsha.student.exception.InstructorNotFoundException;
import com.harsha.student.model.Address;
import com.harsha.student.model.Review;
import com.harsha.student.model.entites.Instructor;
import com.harsha.student.model.entites.InstructorVM;
import com.harsha.student.model.entites.SigninRequest;
import com.harsha.student.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    private Logger logger= LoggerFactory.getLogger(InstructorService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    JWTRetrivalService jwtRetrivalService;
    private static final String ADDRESS_URL="http://localhost:8080/instructor/";
    private static final String REVIEW_URL="http://localhost:8090/reviews/instructor/";
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository)
    {
        this.instructorRepository=instructorRepository;
    }

    public Instructor saveInstructor(Instructor instructor)
    {
        logger.debug("inside Instructor service of saveInstructor method");
        return instructorRepository.save(instructor);
    }

    public List<InstructorVM> getInstructorList()
    {
        List<Instructor> instructors = instructorRepository.findAll();
        return mapToInstructorVM(instructors);
    }

    private List<InstructorVM> mapToInstructorVM(List<Instructor> instructors)
    {
   return    instructors.stream().map(
                instructor -> {
                    InstructorVM instructorVM = new InstructorVM();
                    instructorVM.setInstructorId(instructor.getInstructorId());
                    instructorVM.setInstructorName(instructor.getInstructorName());
                    instructorVM.setInstructorEmail(instructor.getInstructorEmail());
                    instructorVM.setInstructorMobile(instructor.getInstructorMobile());
                    instructorVM.setInstructorDetail(instructor.getInstructorDetail());
                    instructorVM.setCourses(instructor.getCourses());
                    instructorVM.setAddress(getAddressFromAddressService(instructor.getInstructorId()));
                    instructorVM.setReviews(getReviewsFromReviewService(instructor.getInstructorId()));
                    return instructorVM;
                }).collect(Collectors.toList());
    }

    private List<Review> getReviewsFromReviewService(Integer instructorId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(REVIEW_URL)
                .queryParam("id", "{instructorId}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        final String s = String.valueOf(instructorId);
        params.put("instructorId", s);
        HttpEntity<List<Review>> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Review>>() {},
                params
        );
    return response.getBody();
    }

    public Instructor getInstructorById(Integer instructorId) throws InstructorNotFoundException {
        Instructor instructor= instructorRepository.findById(instructorId).orElseThrow(()->new InstructorNotFoundException("instructor with the id :"+ instructorId +"is not found"));


       return instructor;

    }

    public InstructorVM getInstructorByName(String instructorName) throws InstructorNotFoundException {
        Instructor instructor= instructorRepository.findByInstructorName(instructorName).
                orElseThrow(()->new InstructorNotFoundException("instructor with the name :"+ instructorName +"is not found"));
        InstructorVM instructorVM=new InstructorVM();
        instructorVM.setInstructorMobile(instructor.getInstructorMobile());
        instructorVM.setInstructorDetail(instructor.getInstructorDetail());
        instructorVM.setInstructorName(instructor.getInstructorName());
        instructorVM.setCourses(instructor.getCourses());
        instructorVM.setAddress(getAddressFromAddressService(instructor.getInstructorId()));
        instructorVM.setInstructorEmail(instructor.getInstructorEmail());
        instructorVM.setInstructorId(instructor.getInstructorId());
        instructorVM.setReviews(getReviewsFromReviewService(instructor.getInstructorId()));
        return instructorVM;
    }

    private Address getAddressFromAddressService(Integer instructorId)
    {
        SigninRequest jwtRetrivalVM= new SigninRequest();
        jwtRetrivalVM.setUsername("harsha");
        jwtRetrivalVM.setPassword("abc");
        final String jwt = jwtRetrivalService.getJWT(jwtRetrivalVM);
        HttpHeaders headers = new org.springframework.http.HttpHeaders();

        headers.set("application-name","student-service");
        headers.set("Authorization", "Bearer"+" "+ jwt);

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity< Address> response = restTemplate.exchange(ADDRESS_URL+instructorId,
                HttpMethod.GET,entity, new ParameterizedTypeReference<Address>() {});
        return response.getBody();

    }
    }
