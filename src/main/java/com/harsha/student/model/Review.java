package com.harsha.student.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString

public class Review {

    private List<ReviewDetails> reviewDetails=new ArrayList<>();
    private String instructorId;
}
