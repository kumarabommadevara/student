package com.harsha.student;

import com.harsha.student.model.entites.Instructor;
import com.harsha.student.model.entites.InstructorDetail;
import com.harsha.student.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {

	@Autowired
	private  InstructorService instructorService;

	private Logger logger=LoggerFactory.getLogger(StudentApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		save();

findAllInstructors();
	}

	private void save() {
		logger.debug("inside run method of StudentApplication");
		Instructor instructor=new Instructor("Harsha","Kumarahb@yahoo.com","8919186066");
		InstructorDetail instructorDetail=new InstructorDetail("watching youtube","Msters");
		instructor.setInstructorDetail(instructorDetail);
		instructorService.saveInstructor(instructor);
		logger.debug("instuctor saved to DB");
		Instructor instructor1=new Instructor("sai","sai@yahoo.com","53546464646");
		InstructorDetail instructorDetail1=new InstructorDetail("watching movies","Btech");
		instructor1.setInstructorDetail(instructorDetail1);
		instructorService.saveInstructor(instructor1);
		logger.debug("instuctor saved to DB {}" +instructor1);
	}

	private void findAllInstructors()
	{
		logger.debug("getting all instructors from DB");
		List<Instructor> instructorList = instructorService.getInstructorList();
		logger.debug("Available instructors in Db {} "+ instructorList);

		 getInstructorDetails(instructorList);

	}

	private static void getInstructorDetails(List<Instructor> instructorList) {
		instructorList.stream()
				.forEach(instructor -> System.out.println(instructor.getInstructorDetail()));


	}
}
