package com.harsha.student;

import com.harsha.student.exception.InstructorNotFoundException;
import com.harsha.student.model.entites.Course;
import com.harsha.student.model.entites.Instructor;
import com.harsha.student.model.entites.InstructorDetail;
import com.harsha.student.model.entites.Student;
import com.harsha.student.service.InstructorService;
import com.harsha.student.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {

    @Autowired
    private InstructorService instructorService;
    @Autowired
    private StudentService studentService;

    private Logger logger = LoggerFactory.getLogger(StudentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        save();
        //findAllInstructors();
        //findInstructorById();
        //	findInstructorByName();
    }


    private void save() {
        logger.debug("inside run method of StudentApplication");
        Instructor instructor = new Instructor("Harsha", "Kumarahb@yahoo.com", "8919186066");
        InstructorDetail instructorDetail = new InstructorDetail("watching youtube", "Msters");
        instructor.setInstructorDetail(instructorDetail);
        Course course = new Course("java", "5000");
        Course course1 = new Course("springboot", "10000");
        course.setInstructor(instructor);
        course1.setInstructor(instructor);
        instructor.setCourses(Arrays.asList(course, course1));

        instructorService.saveInstructor(instructor);
        logger.debug("instuctor saved to DB");
        Instructor instructor1 = new Instructor("sai", "sai@yahoo.com", "53546464646");
        InstructorDetail instructorDetail1 = new InstructorDetail("watching movies", "Btech");
        instructor.setInstructorDetail(instructorDetail);
        Course course2 = new Course("salesforce", "15000");
        course2.setInstructor(instructor1);
        instructor1.setInstructorDetail(instructorDetail1);
        instructor1.setCourses(Arrays.asList(course2));
        instructorService.saveInstructor(instructor1);

        Student student = new Student("john", "john@gmail.com");
        Student student1 = new Student("rob", "rob@gmail.com");
        Student student2 = new Student("alex", "alex@gmail.com");
        Student student3 = new Student("king", "king@gmail.com");
        Student student4 = new Student("mary", "mary@gmail.com");
        student4.setCourses(Arrays.asList(course1,course2));
		student1.setCourses(Arrays.asList(course));
		student3.setCourses(Arrays.asList(course));

		studentService.saveStudent(Arrays.asList(student,student1,student2,student3,student4));

        logger.debug("instuctor saved to DB {}" + instructor1);
    }

    private void findAllInstructors() {
        logger.debug("getting all instructors from DB");
        List<Instructor> instructorList = instructorService.getInstructorList();
        logger.debug("Available instructors in Db {} " + instructorList);

        getInstructorDetails(instructorList);

    }

    private static void getInstructorDetails(List<Instructor> instructorList) {
        instructorList.stream()
                .forEach(instructor -> System.out.println(instructor.getInstructorDetail()));

    }

    private void findInstructorById() throws InstructorNotFoundException {
        Instructor instructor = instructorService.getInstructorById(5);
        logger.debug("instructor with id {} " + instructor);
    }

    private void findInstructorByName() throws InstructorNotFoundException {
        Instructor instructor = instructorService.getInstructorByName("harsha");
        logger.debug("instructor with name {} " + instructor);
    }
}