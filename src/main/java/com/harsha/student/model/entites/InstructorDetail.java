package com.harsha.student.model.entites;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "instructorDetails")
@ToString
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer instructorDetailsId;
    @Column(name = "hobby")
    private String  instructorHobby;
    @Column(name = "education")
    private String  education;

    public InstructorDetail(){}
    public InstructorDetail(String instructorHobby, String education) {
        this.instructorHobby = instructorHobby;
        this.education = education;
    }
}
