package com.cagri.studentapi.util;

import com.cagri.studentapi.dto.StudentRequest;
import com.cagri.studentapi.dto.StudentResponse;
import com.cagri.studentapi.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    public Student toEntity(StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setStudentNumber(studentRequest.getStudentNumber());
        return student;
    }

    public StudentResponse toResponse(Student student) {
        if (student == null) {
            return null;
        }

        return new StudentResponse(

                student.getName(),
                student.getSurname(),
                student.getStudentNumber()

        );
    }
}
