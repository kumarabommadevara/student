package com.harsha.student.model.entites;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "instructors")
@ToString(exclude ="courses")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id")
    private Integer instructorId;
    @Column(name = "instructor_name")
    private String  instructorName;
    @Column(name = "email")
    private String  instructorEmail;
    @Column(name = "mobile")
    private String instructorMobile;
    @OneToOne(cascade = CascadeType.ALL)
    private InstructorDetail instructorDetail;
    @OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL)
    private List<Course> courses=new ArrayList<>();

    public Instructor()
    {}
    public Instructor(String instructorName, String instructorEmail, String instructorMobile) {
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorMobile = instructorMobile;
    }
}
