package com.cagri.studentapi.service;

import com.cagri.studentapi.dto.StudentRequest;
import com.cagri.studentapi.dto.StudentResponse;

public interface StudentService {


    StudentResponse getStudentById(long id);

    StudentResponse getStudentByStudentNumber(String studentNumber);

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(long id, StudentRequest studentRequest);

    StudentResponse updateStudentByStudentNumber(String studentNumber, StudentRequest studentRequest);


}
