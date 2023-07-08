package com.harsha.student.repository;

import com.harsha.student.model.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

@Query(value = "SELECT s.STUDENT_ID ,s.STUDENT_EMAIL ,s.STUDENT_NAME ,c.FEE \n" +
        "FROM STUDENTS s\n" +
        "join COURSE_STUDENT cs\n" +
        "join COURSES c\n" +
        "on s.STUDENT_ID =cs.STUDENT_ID \n" +
        "and c.ID=cs.COURSE_ID \n" +
        "WHERE c.COURSE_NAME=:courseName",nativeQuery = true)
    List<Student> findAllStudentsByCourseName(String courseName);
}
