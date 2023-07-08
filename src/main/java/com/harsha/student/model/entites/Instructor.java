package com.harsha.student.model.entites;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "instructors")
@ToString
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer instructorId;
    @Column(name = "name")
    private String  instructorName;
    @Column(name = "email")
    private String  instructorEmail;
    @Column(name = "mobile")
    private String instructorMobile;
    @OneToOne(cascade = CascadeType.ALL)
    private InstructorDetail instructorDetail;

    public Instructor()
    {}
    public Instructor(String instructorName, String instructorEmail, String instructorMobile) {
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorMobile = instructorMobile;
    }
}
