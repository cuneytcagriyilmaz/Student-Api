package com.cagri.studentapi.service;

import com.cagri.studentapi.dto.StudentRequest;
import com.cagri.studentapi.dto.StudentResponse;
import com.cagri.studentapi.entity.Student;
import com.cagri.studentapi.exceptions.StudentNumberDuplicateException;
import com.cagri.studentapi.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponse getStudentById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return convertToStudentResponse(student);
    }

    @Override
    public StudentResponse getStudentByStudentNumber(String studentNumber) {
        Student student = studentRepository.findStudentByStudentNumber(studentNumber);
        if (student == null) {
            throw new RuntimeException("Student not found with studentNumber: " + studentNumber);
        }
        return convertToStudentResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse saveStudent(StudentRequest studentRequest) {
         if (studentRepository.existsByStudentNumber(studentRequest.getStudentNumber())) {
            throw new StudentNumberDuplicateException(studentRequest.getStudentNumber());
        }

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setStudentNumber(studentRequest.getStudentNumber());
        Student savedStudent = studentRepository.save(student);
        return convertToStudentResponse(savedStudent);
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setStudentNumber(studentRequest.getStudentNumber());
        Student updatedStudent = studentRepository.save(student);
        return convertToStudentResponse(updatedStudent);
    }


    @Override
    @Transactional
    public StudentResponse updateStudentByStudentNumber(String studentNumber, StudentRequest studentRequest) {
        Student student = studentRepository.findStudentByStudentNumber(studentNumber);
        if (student == null) {
            throw new RuntimeException("Student not found with studentNumber: " + studentNumber);
        }
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setStudentNumber(studentRequest.getStudentNumber());
        Student updatedStudent = studentRepository.save(student);
        return convertToStudentResponse(updatedStudent);
    }

    private StudentResponse convertToStudentResponse(Student student) {
        return new StudentResponse(student.getName(), student.getSurname(), student.getStudentNumber());
    }
}
