package com.cagri.studentapi.exceptions;

public class StudentNumberDuplicateException extends RuntimeException {


    public StudentNumberDuplicateException(String studentNumber) {
        super("Student with studentNumber: " + studentNumber + " already exists.");
    }
}
