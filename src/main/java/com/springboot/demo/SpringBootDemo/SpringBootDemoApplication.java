package com.springboot.demo.SpringBootDemo;

import com.springboot.demo.SpringBootDemo.dao.LearningAppDAO;
import com.springboot.demo.SpringBootDemo.dao.StudentDAO;
import com.springboot.demo.SpringBootDemo.entity.Course;
import com.springboot.demo.SpringBootDemo.entity.Instructor;
import com.springboot.demo.SpringBootDemo.entity.InstructorDetail;
import com.springboot.demo.SpringBootDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Bean
	@Profile("learningapp")
	public CommandLineRunner learningAppCommandLineRunner(LearningAppDAO learningAppDAO) {
		return runner -> {
			createInstructor(learningAppDAO);
			findInstructor(learningAppDAO);
			deleteInstructor(learningAppDAO);
			findInstructorDetail(learningAppDAO);
			deleteInstructorDetail(learningAppDAO);
			createInstructorWithCourses(learningAppDAO);
//			findInstructorWithCourses(learningAppDAO);
			findCoursesForInstructor(learningAppDAO);
		};
	}

	private void createInstructor(LearningAppDAO learningAppDAO) {
		// create the instructor
		Instructor instructor1 = new Instructor("Chad", "Darby", "luv2code@email.com");
		Instructor instructor2 = new Instructor("Cynwell", "Liao", "cynwell@email.com");
		// create the instructor detail
		InstructorDetail instructorDetail1 = new InstructorDetail("https://www.youtube.com/@luv2code", "Luv 2 Code!");
		InstructorDetail instructorDetail2 = new InstructorDetail("https://www.youtube.com/@cynwell_1iao", "Learn Spring Boot!");
		// associate the objects
		instructor1.setInstructorDetail(instructorDetail1);
		instructor2.setInstructorDetail(instructorDetail2);
		// save the instructor
		// NOTE: this will also save the details object because of CascadeType.ALL in InstructorDetail class
		System.out.println("Saving instructor: "  + instructor1);
		learningAppDAO.save(instructor1);
		System.out.println("Saving instructor: "  + instructor2);
		learningAppDAO.save(instructor2);
		System.out.println("createInstructor Done");
	}

	private void findInstructor(LearningAppDAO learningAppDAO) {
		int id = 1;
		System.out.println("Finding instructor by id: " + id);
		Instructor instructor = learningAppDAO.findInstructorById(id);
		System.out.println("Found instructor: " + instructor);
		System.out.println("The accociated instructorDetail only: " + instructor.getInstructorDetail());
		System.out.println("findInstructor Done");
	}

	private void deleteInstructor(LearningAppDAO learningAppDAO) {
		int id = 1;
		System.out.println("Deleting instructor by id: " + id);
		learningAppDAO.deleteInstructorById(id);
		System.out.println("deleteInstructor Done");
	}

	private void findInstructorDetail(LearningAppDAO learningAppDAO) {
		// get the instructor detail object
		int id = 2;
		InstructorDetail instructorDetail = learningAppDAO.findInstructorDetailById(id);
		// print the instructor detail
		System.out.println("Found instructorDetail: " + instructorDetail);
		// print the associated instructor
		System.out.println("The associated instructor: " + instructorDetail.getInstructor());
		System.out.println("findInstructorDetail Done");
	}

	private void deleteInstructorDetail(LearningAppDAO learningAppDAO) {
		int id = 2;
		System.out.println("Deleting instructorDetail by id: " + id);
		learningAppDAO.deleteInstructorDetailById(id);
		System.out.println("deleteInstructorDetail Done");
	}


	private void createInstructorWithCourses(LearningAppDAO learningAppDAO) {
		// create the instructor
		Instructor instructor = new Instructor("Chad", "Darby", "luv2code@email.com");
		// create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/@luv2code", "Luv 2 Code!");
		// associate the objects
		instructor.setInstructorDetail(instructorDetail);
		// create some courses
		Course course1 = new Course("Spring Boot 3, Spring 6 & Hibernate for Beginners");
		Course course2 = new Course("FastAPI - The Complete Course 2025 (Beginner + Advanced)");
		Course course3 = new Course("Spring Boot Unit Testing with JUnit, Mockito and MockMvc");
		// add courses to instructor
		instructor.add(course1);
		instructor.add(course2);
		instructor.add(course3);
		// save the instructor
		// NOTE: this will also save the courses because of CascadeType.PERSIST
		System.out.println("Saving instructor: "  + instructor);
		System.out.println("The courses: " + instructor.getCourses());
		learningAppDAO.save(instructor);
		System.out.println("createInstructorWithCourses Done");
	}

	private void findInstructorWithCourses(LearningAppDAO learningAppDAO) {
		int id = 3;
		System.out.println("Finding instructor by id: " + id);
		Instructor instructor = learningAppDAO.findInstructorById(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("The associated courses: " + instructor.getCourses());
		System.out.println("findInstructorWithCourses Done");
	}

	private void findCoursesForInstructor(LearningAppDAO learningAppDAO) {
		int id = 3;
		System.out.println("Finding instructor by id: " + id);
		Instructor instructor = learningAppDAO.findInstructorById(id);
		System.out.println("Instructor: " + instructor);
		// find courses for instructor
		System.out.println("Finding Courses for instructor id: " + id);
		List<Course> courses = learningAppDAO.findCoursesByInstructorId(id);
		// associate the objects
		instructor.setCourses(courses);
		System.out.println("The associated courses: " + instructor.getCourses());
		System.out.println("findCoursesForInstructor Done");
	}

	@Bean
	@Profile("student")
	public CommandLineRunner studentCommandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			readStudent(studentDAO);
			queryForStudent(studentDAO);
			queryForStudentByLastName(studentDAO);
			updateStudent(studentDAO);
			deleteStudent(studentDAO);
			deleteAllStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object ...");
		Student student = new Student("Hsing-Wei", "Liao", "email@gmail.com");

		// save the student object
		System.out.println("Saving student object ...");
		studentDAO.save(student);

		//display id of the saved student
		System.out.println("Saved student object. Generated id: " + student.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating multiple students object ...");
		Student student1 = new Student("Student1", "Cheng", "Student1Cheng1@gmail.com");
		Student student2 = new Student("Student2", "Liao", "Student2Liao2@gmail.com");
		Student student3 = new Student("Student3", "Cheng", "Student3Cheng3@gmail.com");

		// save the student objects
		System.out.println("Creating multiple students object ...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

		System.out.println("Saved student object. Generated id: " + student1.getId());
		System.out.println("Saved student object. Generated id: " + student2.getId());
		System.out.println("Saved student object. Generated id: " + student3.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int id = 1;
		System.out.println("Reading student data with id: " + id);
		Student student = studentDAO.findById(id);
		// display student
		System.out.println("Found the student: " + student);
	}

	private void queryForStudent(StudentDAO studentDAO) {
		// get a list of students
		System.out.println("Querying for students: ");
		List<Student> students = studentDAO.findAll();
		// display list of students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {
		// get a list of students
		System.out.println("Querying for students by last name");
		List<Student> students = studentDAO.findByLastName("Liao");
		// display list of students
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id " + studentId);
		Student student = studentDAO.findById(studentId);
		// change first name to "Cynwell"
		System.out.println("Updating student with id " + studentId);
		student.setFirstName("Cynwell");
		// update the student
		studentDAO.update(student);
		// display the updated student
		System.out.println("Updated student: " + student);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student with id " + studentId);
		studentDAO.delete(studentId);
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numDeleted = studentDAO.deleteAll();
		System.out.println("Deleted all students: " + numDeleted);
	}
}
