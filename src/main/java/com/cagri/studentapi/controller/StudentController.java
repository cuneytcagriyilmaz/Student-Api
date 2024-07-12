package com.cagri.studentapi.controller;

import com.cagri.studentapi.dto.StudentRequest;
import com.cagri.studentapi.dto.StudentResponse;
import com.cagri.studentapi.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        StudentResponse savedStudent = studentService.saveStudent(studentRequest);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") long id, @RequestBody StudentRequest studentRequest) {
        StudentResponse updatedStudent = studentService.updateStudent(id, studentRequest);
        return updatedStudent != null ? new ResponseEntity<>(updatedStudent, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/studentNumber/{studentNumber}")
    public ResponseEntity<StudentResponse> updateStudentByStudentNumber(@PathVariable("studentNumber") String studentNumber, @Valid @RequestBody StudentRequest studentRequest) {
        StudentResponse updatedStudent = studentService.updateStudentByStudentNumber(studentNumber, studentRequest);
        return ResponseEntity.ok(updatedStudent);
    }


}
