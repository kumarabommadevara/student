package com.harsha.student.controller;

import com.harsha.student.exception.InstructorNotFoundException;
import com.harsha.student.exception.StudentNotFoundException;
import com.harsha.student.model.entites.Instructor;
import com.harsha.student.model.entites.Student;
import com.harsha.student.service.InstructorService;
import com.harsha.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> studentList = studentService.findAllStudents();

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/students/course/{name}")
    public ResponseEntity<List<Student>> findAllStudentsByCourseName(@PathVariable("name") String name) throws InstructorNotFoundException, StudentNotFoundException {
        List<Student> studentList= studentService.findAllStudentsByCourseName(name);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

}