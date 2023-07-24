package com.harsha.student.controller;

import com.harsha.student.exception.InstructorNotFoundException;
import com.harsha.student.model.entites.Instructor;
import com.harsha.student.model.entites.InstructorVM;
import com.harsha.student.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstructorController {

private  final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<InstructorVM>> getInstructors()
    {
        List<InstructorVM> instructorList = instructorService.getInstructorList();

        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }
    @GetMapping("/instructor/{name}")
    public ResponseEntity<InstructorVM> getInstructorByName(@PathVariable("name") String name) throws InstructorNotFoundException {
        InstructorVM instructor = instructorService.getInstructorByName(name);

        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

}