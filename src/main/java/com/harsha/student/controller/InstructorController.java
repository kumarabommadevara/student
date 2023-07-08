package com.harsha.student.controller;

import com.harsha.student.model.entites.Instructor;
import com.harsha.student.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstructorController {

private  final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> getInstructors()
    {
        List<Instructor> instructorList = instructorService.getInstructorList();

        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }
}