package com.springboot.demo.SpringBootDemo;

import com.springboot.demo.SpringBootDemo.dao.StudentDAO;
import com.springboot.demo.SpringBootDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
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
