package com.harsha.student.model.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harsha.student.model.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class InstructorVM {


    private Integer instructorId;
    private String  instructorName;
    private String  instructorEmail;
    private String instructorMobile;
    private InstructorDetail instructorDetail;
    private List<Course> courses=new ArrayList<>();
    private Address address;

}
