# SpringBootDemo

> **Personal Learning Repo**: My journey of going through an entire Udemy course and combining small Spring Boot exercises into one cohesive codebase.

This repository is **not** a production project. Instead, it brings together multiple mini-projects from a Spring Boot course into a single workspace so I could:

- **Tackle real-world complexity** by resolving minor conflicts and custom merges between sections.
- **Deepen understanding** through debugging and integrating distinct features side-by-side.
- **Reflect on growth**, as each commit represents a new concept mastered, from AOP and security to Thymeleaf and JPA relationships.

---

## Why I Combined Them

Rather than leaving each exercise in isolation, merging them helped me:

- Experience a more complex codebase structure.
- Practice Spring profiles and configuration overrides.
- Learn how different Spring Boot starters interact in the same application.

Throughout this process, I spent hours debugging, refactoring, and experimenting—gaining much more than simply following step-by-step tutorials.

---

## Highlights of My Learning

- **Spring Profiles & Bean Configuration**: Switching between demos (AOP, REST, Thymeleaf, JPA) with custom `@Bean` and lifecycle callbacks.
- **AOP Mastery**: Defined pointcuts, ordered multiple aspects, and used `@Before`, `@AfterReturning`, `@AfterThrowing`, `@After`, and `@Around` advices to log and handle exceptions.
- **REST & Service Layer**: Built CRUD endpoints including `PATCH` with JSON merging, global exception handling via `@ControllerAdvice`, and Spring Data REST integration.
- **Security & JDBC Auth**: Swapped from in-memory auth to JDBC with BCrypt, created custom login/logout pages, mapped roles to URL/method permissions, and secured Actuator endpoints.
- **Thymeleaf & Validation**: Developed web forms with dropdowns, radios, checkboxes, custom constraint annotation, and error messages in `messages.properties`.
- **JPA Relationships**: Implemented One-to-One, One-to-Many (lazy vs eager), and Many-to-Many mappings—cascading saves, joins, and orphan cleanup.
- **Actuator & OpenAPI**: Configured health/info endpoints and Swagger UI with SpringDoc.
- **Git & Commit**: Practiced clear, descriptive commit messages to track progress and maintain project history.

---

## Codebase Structure

```
SprintBootDemo/ (root)
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
├── sql-scripts/
│   ├── create-db-user.sql
│   ├── create-users-and-authorities.sql
│   ├── create-members-and-roles.sql
│   ├── create-employee.sql
│   ├── create-customer.sql
│   ├── create-many-to-many.sql
│   ├── create-one-to-many.sql
│   ├── create-one-to-one.sql
│   └── create-student.sql
├── src/
│   └── main/
│       ├── java/
│       │   └── com/springboot/demo/SpringBootDemo/
│       │       ├── SpringBootDemoApplication.java (main class)
│       │       ├── config/
│       │       │   ├── SecurityConfig.java
│       │       │   └── StudentConfig.java
│       │       ├── aspect/
│       │       │   ├── ApiAnalyticsAspect.java
│       │       │   ├── CloudLogAsyncAspect.java
│       │       │   ├── EmployeeLoggingAspect.java
│       │       │   ├── LoggingAspect.java
│       │       │   └── PointcutExpression.java
│       │       ├── controller/
│       │       │   ├── RestController.java
│       │       │   ├── RestExceptionController.java
│       │       │   └── ThymeleafController.java
│       │       ├── dao/
│       │       │   ├── AccountDAO.java
│       │       │   ├── AccountDAOImpl.java
│       │       │   ├── CustomerRepository.java
│       │       │   ├── EmployeeDAO.java
│       │       │   ├── EmployeeDAOImpl.java
│       │       │   ├── LearningAppDAO.java
│       │       │   ├── LearningAppDAOImpl.java
│       │       │   ├── MembershipDAO.java
│       │       │   ├── MembershipDAOImpl.java
│       │       │   ├── StudentDAO.java
│       │       │   └── StudentDAOImpl.java
│       │       ├── entity/
│       │       │   ├── Account.java
│       │       │   ├── Course.java
│       │       │   ├── Customer.java
│       │       │   ├── Employee.java
│       │       │   ├── Instructor.java
│       │       │   ├── InstructorDetail.java
│       │       │   ├── Learner.java
│       │       │   ├── Review.java
│       │       │   ├── Student.java
│       │       │   └── StudentErrorResponse.java
│       │       ├── service/
│       │       │   ├── EmployeeService.java
│       │       │   ├── EmployeeServiceImpl.java
│       │       │   ├── TrafficFortuneService.java
│       │       │   └── TrafficFortuneServiceImpl.java
│       │       ├── student/
│       │       │   ├── UniversityStudent.java
│       │       │   ├── UniMelbStudent.java
│       │       │   ├── QUTStudent.java
│       │       │   └── MonashUniversityStudent.java
│       │       └── validation/
│       │           ├── CourseCode.java
│       │           └── CourseCodeConstraintValidator.java
│       └── resources/
│           ├── application.properties
│           ├── messages.properties
│           ├── static/
│           │   └── css/
│           │       └── demo.css
│           └── templates/
│               ├── customers/
│               │   ├── customerform-show.html
│               │   └── customerform-confirmation.html
│               ├── employees/
│               │   ├── employee-form.html
│               │   └── employee-list.html
│               ├── securitydemos/
│               │   ├── security-home.html
│               │   ├── security-fancy-login.html
│               │   ├── security-accessdenied.html
│               │   ├── security-general.html
│               │   ├── security-leader.html
│               │   └── security-system.html
│               ├── students/
│               │   ├── studentform-show.html
│               │   └── studentform-confirmation.html
│               └── thymeleafintros/
│                   ├── thymeleafform-show.html
│                   ├── thymeleafform-process.html
│                   └── hellothymeleaf.html
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

## Final Thoughts

This repo captures my hands-on journey through a comprehensive Spring Boot course on Udemy. By uniting these exercises, I’ve gained real experience in:

- Navigating a growing codebase
- Debugging subtle conflicts
- Orchestrating multiple Spring features in one app

Thanks for checking out my personal learning playground—feel free to explore the commits and see how each section evolved!
