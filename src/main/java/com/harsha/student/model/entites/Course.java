package com.harsha.student.model.entites;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses")
@ToString(exclude = "students")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer courseId;
    @Column(name = "course_name")
    private String  courseName;
    @Column(name = "fee")
    private String courseFee;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    @ManyToMany(mappedBy ="courses")
    private List<Student> students=new ArrayList<>();

    public Course()
    {

    }
    public Course(String courseName, String courseFee) {
        this.courseName = courseName;
        this.courseFee = courseFee;
    }
}
