package com.harsha.student.service;

import com.harsha.student.exception.InstructorNotFoundException;
import com.harsha.student.model.Address;
import com.harsha.student.model.entites.Instructor;
import com.harsha.student.model.entites.InstructorVM;
import com.harsha.student.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    private Logger logger= LoggerFactory.getLogger(InstructorService.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String ADDRESS_URL="http://localhost:8080/instructor/";
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
    @Cacheable(value = "itemCache")

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
                    return instructorVM;
                }).collect(Collectors.toList());
    }
    public Instructor getInstructorById(Integer instructorId) throws InstructorNotFoundException {
        return instructorRepository.findById(instructorId).orElseThrow(()->new InstructorNotFoundException("instructor with the id :"+ instructorId +"is not found"));
    }

    public Instructor getInstructorByName(String instructorName) throws InstructorNotFoundException {
        return instructorRepository.findByInstructorName(instructorName).
                orElseThrow(()->new InstructorNotFoundException("instructor with the name :"+ instructorName +"is not found"));
    }

    private Address getAddressFromAddressService(Integer instructorId)
    {
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("application-name", "harsha");

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity< Address> response = restTemplate.exchange(ADDRESS_URL+instructorId,
                HttpMethod.GET,entity, new ParameterizedTypeReference<Address>() {});
        return response.getBody();

    }
    }
