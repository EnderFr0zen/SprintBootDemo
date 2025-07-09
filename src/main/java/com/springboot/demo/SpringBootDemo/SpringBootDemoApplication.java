package com.springboot.demo.SpringBootDemo;

import com.springboot.demo.SpringBootDemo.dao.AccountDAO;
import com.springboot.demo.SpringBootDemo.dao.LearningAppDAO;
import com.springboot.demo.SpringBootDemo.dao.MembershipDAO;
import com.springboot.demo.SpringBootDemo.dao.StudentDAO;
import com.springboot.demo.SpringBootDemo.entity.*;
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
	@Profile("springaop")
	public CommandLineRunner springAOPCommandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		return runner -> {
			demoBeforeAdvice(accountDAO, membershipDAO);
			demoAfterReturningAdvice(accountDAO);
			demoAfterThrowingAdvice(accountDAO);
			demoAfterAdvice(accountDAO);
		};
	}

	private void demoBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		System.out.println("\n| demoBeforeAdvice |");
		// call the business method
		Account account = new Account("Cynwell", "Diamond");
		accountDAO.addAccount(account, true);
		accountDAO.doWork();
		membershipDAO.addAccount();
		membershipDAO.addStar();
		membershipDAO.addBoolean();
		// call getters/setters for practice AOP Combining Pointcuts concept
		accountDAO.setName("Cynwell");
		accountDAO.setServiceCode("Diamond");
		String name = accountDAO.getName();
		String serviceCode = accountDAO.getServiceCode();
	}

	private void demoAfterReturningAdvice(AccountDAO accountDAO) {
		System.out.println("\n| demoAfterReturningAdvice |");
		// call method to find the accounts
		List<Account> accounts = accountDAO.findAccounts();
		// display accounts
		System.out.println(accounts);
	}

	private void demoAfterThrowingAdvice(AccountDAO accountDAO) {
		System.out.println("\n| demoAfterThrowingAdvice |");
		// call method to find the accounts
		List<Account> accounts = null;
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("Catch Exception: " + e.getMessage());
		}
		// display accounts
		System.out.println(accounts);
	}

	private void demoAfterAdvice(AccountDAO accountDAO) {
		System.out.println("\n| demoAfterAdvice |");
		// call method to find the accounts
		List<Account> accounts = null;
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("Catch Exception: " + e.getMessage());
		}
		// display accounts
		System.out.println(accounts);
	}

	@Bean
	@Profile("learningapp")
	public CommandLineRunner learningAppCommandLineRunner(LearningAppDAO learningAppDAO) {
		return runner -> {
			createInstructor(learningAppDAO);
			findInstructor(learningAppDAO);
			findInstructorDetail(learningAppDAO);
			createInstructorWithCourses(learningAppDAO);
			findInstructorWithCourses(learningAppDAO);
			findCoursesForInstructor(learningAppDAO);
			findInstructorWithCoursesJoinFetch(learningAppDAO);
			updateInstructor(learningAppDAO);
			updateCourse(learningAppDAO);
			deleteInstructor(learningAppDAO);
			createCourseAndReviews(learningAppDAO);
			retrieveCourseAndReviews(learningAppDAO);
			deleteCourseAndReviews(learningAppDAO);
			createCourseAndLearners(learningAppDAO);
			findCourseAndLearners(learningAppDAO);
			addMoreCoursesForLearner(learningAppDAO);
			findLearnerAndCourses(learningAppDAO);
			deleteInstructorDetail(learningAppDAO);
			deleteInstructor(learningAppDAO);
			deleteCourse(learningAppDAO);
			deleteLearner(learningAppDAO);
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
		int id = 3;
		System.out.println("Deleting instructor by id: " + id);
		learningAppDAO.deleteInstructorById(id);
		System.out.println("deleteInstructor Done");
	}

	private void findInstructorDetail(LearningAppDAO learningAppDAO) {
		// get the instructor detail object
		int id = 1;
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
		Instructor instructor = learningAppDAO.findInstructorByIdJoinFetch(id);
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

	private void findInstructorWithCoursesJoinFetch(LearningAppDAO learningAppDAO) {
		int id = 3;
		// find the instructor
		System.out.println("Finding instructor by id: " + id);
		Instructor instructor = learningAppDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("The  associated courses: " + instructor.getCourses());
		System.out.println("findInstructorWithCoursesJoinFetch Done");
	}


	private void updateInstructor(LearningAppDAO learningAppDAO) {
		int id = 3;
		// find the instructor
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = learningAppDAO.findInstructorById(id);
		// update the instructor
		System.out.println("Updating instructor id: " + id);
		instructor.setLastName("TEST");
		learningAppDAO.update(instructor);
		System.out.println("updateInstructor Done");
	}

	private void updateCourse(LearningAppDAO learningAppDAO) {
		int id = 10001;
		// find the course
		System.out.println("Finding course id: " + id);
		Course course = learningAppDAO.findCourseById(id);
		// update the course
		System.out.println("Updating course id: " + id);
		course.setTitle("[NEW] Spring Boot 3, Spring 6 & Hibernate for Beginners");
		learningAppDAO.update(course);
		System.out.println("updateCourse Done");
	}

	private void deleteCourse(LearningAppDAO learningAppDAO) {
		int id = 10002;
		System.out.println("Deleting course id: " + id);
		learningAppDAO.deleteCourseById(id);
		System.out.println("deleteCourse Done");
	}

	private void createCourseAndReviews(LearningAppDAO learningAppDAO) {
		// create a course
		Course course = new Course("Pacman - How To Score One Million Points");
		// add some reviews
		course.addReview(new Review("Great course ... loved it!"));
		course.addReview(new Review("Cool course ... well done!"));
		course.addReview(new Review("Ohh ..."));
		// save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());
		learningAppDAO.save(course);
		System.out.println("createCourseAndReviews Done");
	}

	private void retrieveCourseAndReviews(LearningAppDAO learningAppDAO) {
		// get the course and reviews
		int id = 10004;
		Course course = learningAppDAO.findCourseAndReviewsByCourseId(id);
		// print the course
		System.out.println(course);
		// print the reviews
		System.out.println(course.getReviews());
		System.out.println("retrieveCourseAndReviews Done");
	}

	private void deleteCourseAndReviews(LearningAppDAO learningAppDAO) {
		int id = 10004;
		System.out.println("Deleting course id: " + id);
		// this will Cascade delete the course reviews
		learningAppDAO.deleteCourseById(id);
		System.out.println("deleteCourseAndReviews Done");
	}

	private void createCourseAndLearners(LearningAppDAO learningAppDAO) {
		// create a course
		Course course = new Course("Introduction to Java");
		// create the students
		Learner learner1 = new Learner("Cynwell", "Liao", "cynwell@email.com");
		Learner learner2 = new Learner("Ninni", "Yang", "ninni@email.com");
		Learner learner3 = new Learner("Test", "Fordelete", "test@email.com");
		// add students to the course
		course.addLearner(learner1);
		course.addLearner(learner2);
		course.addLearner(learner3);
		// save the course and associated students
		System.out.println("Saving the course: " + course);
		System.out.println("The associated learners: " + course.getLearners());
		// this will Cascade save to entities
		learningAppDAO.save(course);
		System.out.println("createCourseAndLearners Done");
	}

	private void findCourseAndLearners(LearningAppDAO learningAppDAO) {
		int id = 10005;
		Course course = learningAppDAO.findCourseAndLearnersByCourseId(id);
		System.out.println("Loaded course: " + course);
		System.out.println("Learners: " + course.getLearners());
		System.out.println("findCourseAndLearners Done");
	}

	private void findLearnerAndCourses(LearningAppDAO learningAppDAO) {
		int id = 1;
		Learner learner = learningAppDAO.findLearnerAndCoursesByLearnerId(id);
		System.out.println("Loaded learner: " + learner);
		System.out.println("Courses: " + learner.getCourses());
		System.out.println("findLearnerAndCourses Done");
	}

	private void addMoreCoursesForLearner(LearningAppDAO learningAppDAO) {
		int id = 1;
		Learner learner = learningAppDAO.findLearnerAndCoursesByLearnerId(id);
		// create more courses
		Course course1 = new Course("Algorithms and Complexity");
		Course course2 = new Course("Introduction to Spring Boot");
		// add courses to learner
		learner.addCourse(course1);
		learner.addCourse(course2);
		System.out.println("Updating learner: " + learner);
		System.out.println("The associated courses: " + learner.getCourses());
		learningAppDAO.update(learner);
		System.out.println("addMoreCoursesForLearner Done");
	}

	private void deleteLearner(LearningAppDAO learningAppDAO) {
		int id = 3;
		System.out.println("Deleting learner id: " + id);
		System.out.println(learningAppDAO.findLearnerAndCoursesByLearnerId(id));
		learningAppDAO.deleteLearnerById(id);
		System.out.println("deleteLearner Done");
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
