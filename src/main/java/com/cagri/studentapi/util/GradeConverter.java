package com.cagri.studentapi.util;

import com.cagri.studentapi.dto.GradeRequest;
import com.cagri.studentapi.dto.GradeResponse;
import com.cagri.studentapi.entity.Grade;
import com.cagri.studentapi.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class GradeConverter {

    public Grade toEntity(GradeRequest gradeRequest, Student student) {
        if (gradeRequest == null) {
            return null;
        }

        Grade grade = new Grade();
        grade.setCode(gradeRequest.getCode());
        grade.setValue(gradeRequest.getValue());
        grade.setStudent(student);
        return grade;
    }

    public GradeResponse toResponse(Grade grade) {
        if (grade == null) {
            return null;
        }

        return new GradeResponse(
                grade.getStudent().getName(),
                grade.getStudent().getSurname(),
                grade.getStudent().getStudentNumber(),
                grade.getStudent().getGradeList());

    }
}
