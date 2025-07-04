package com.springboot.demo.SpringBootDemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();
    }

    @Override
    public boolean isValid(String courseCode, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if (courseCode != null) {
            result = courseCode.startsWith(coursePrefix);
        } else {
            result = true;
        }
        return result;
    }
}
