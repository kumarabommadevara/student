package com.harsha.student.service;

import com.harsha.student.model.entites.Student;
import com.harsha.student.repository.InstructorRepository;
import com.harsha.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository=studentRepository;
    }

    public void saveStudent(List<Student> students)
    {
        studentRepository.saveAll(students);
    }
}
