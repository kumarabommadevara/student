package com.harsha.student.service;

import com.harsha.student.exception.StudentNotFoundException;
import com.harsha.student.model.entites.Course;
import com.harsha.student.model.entites.Student;
import com.harsha.student.repository.InstructorRepository;
import com.harsha.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Student> findAllStudents() {

      return   studentRepository.findAll();
    }

    public List<Student> findAllStudentsByCourseName(String courseName) throws StudentNotFoundException {

        List<Student> allStudentsByCourseName = studentRepository.findAllStudentsByCourseName(courseName);


        if(allStudentsByCourseName.isEmpty())
        {
            throw  new StudentNotFoundException("No students found with the course "+ courseName);
        }

        return allStudentsByCourseName;
    }


}
