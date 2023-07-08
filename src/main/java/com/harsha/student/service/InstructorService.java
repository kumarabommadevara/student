package com.harsha.student.service;

import com.harsha.student.StudentApplication;
import com.harsha.student.exception.InstructorNotFoundException;
import com.harsha.student.model.entites.Instructor;
import com.harsha.student.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private Logger logger= LoggerFactory.getLogger(InstructorService.class);
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

    public List<Instructor> getInstructorList()
    {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Integer instructorId) throws InstructorNotFoundException {
        return instructorRepository.findById(instructorId).orElseThrow(()->new InstructorNotFoundException("instructor with the id :"+ instructorId +"is not found"));
    }

    public Instructor getInstructorByName(String instructorName) throws InstructorNotFoundException {
        return instructorRepository.findByInstructorName(instructorName).
                orElseThrow(()->new InstructorNotFoundException("instructor with the name :"+ instructorName +"is not found"));
    }
    }
