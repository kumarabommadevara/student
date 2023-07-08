package com.harsha.student.repository;

import com.harsha.student.model.entites.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

    Optional<Instructor> findByInstructorName(String instructorName);
}
