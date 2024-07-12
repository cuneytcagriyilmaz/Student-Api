package com.cagri.studentapi.service;

import com.cagri.studentapi.dto.GradeRequest;
import com.cagri.studentapi.dto.GradeResponse;
import com.cagri.studentapi.entity.Grade;
import com.cagri.studentapi.entity.Student;
import com.cagri.studentapi.repository.GradeRepository;
import com.cagri.studentapi.repository.StudentRepository;
import com.cagri.studentapi.util.GradeConverter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final GradeConverter gradeConverter;




    @Override
    public GradeResponse addGrade(GradeRequest gradeRequest) {
        Student student = studentRepository.findStudentByStudentNumber(gradeRequest.getStudentNumber());
        if (student == null) {
            throw new RuntimeException("Student not found with studentNumber: " + gradeRequest.getStudentNumber());
        }

        Grade existingGrade = gradeRepository.findByStudentAndCode(student, gradeRequest.getCode());
        if (existingGrade != null) {
             double newValue = (existingGrade.getValue() + gradeRequest.getValue()) / 2.0;
            existingGrade.setValue((int) newValue);
            Grade savedGrade = gradeRepository.save(existingGrade);
            return gradeConverter.toResponse(savedGrade);
        } else {
             Grade grade = new Grade();
            grade.setCode(gradeRequest.getCode());
            grade.setValue(gradeRequest.getValue());
            grade.setStudent(student);
            Grade savedGrade = gradeRepository.save(grade);
            return gradeConverter.toResponse(savedGrade);
        }
    }



    @Override
    @Transactional
    public GradeResponse updateGrade(String studentNumber, GradeRequest gradeRequest) {
        Student student = studentRepository.findStudentByStudentNumber(studentNumber);
        if (student == null) {
            throw new RuntimeException("Student not found with studentNumber: " + studentNumber);
        }

        List<Grade> grades = student.getGradeList();
        for (Grade grade : grades) {
            if (grade.getCode().equals(gradeRequest.getCode())) {
                grade.setValue(gradeRequest.getValue());
                Grade updatedGrade = gradeRepository.save(grade);
                return gradeConverter.toResponse(updatedGrade);
            }
        }

        throw new RuntimeException("Grade not found for studentNumber: " + studentNumber + " and code: " + gradeRequest.getCode());
    }
}




