//package com.cagri.studentapi.controller;
//
//import com.cagri.studentapi.dto.StudentRequest;
//import com.cagri.studentapi.dto.StudentResponse;
//import com.cagri.studentapi.service.StudentService;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/student")
//public class StudentController {
//
//    private final StudentService studentService;
//
//
//    @PostMapping
//    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
//        StudentResponse savedStudent = studentService.saveStudent(studentRequest);
//        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") long id, @RequestBody StudentRequest studentRequest) {
//        StudentResponse updatedStudent = studentService.updateStudent(id, studentRequest);
//        return updatedStudent != null ? new ResponseEntity<>(updatedStudent, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("/studentNumber/{studentNumber}")
//    public ResponseEntity<StudentResponse> updateStudentByStudentNumber(@PathVariable("studentNumber") String studentNumber, @Valid @RequestBody StudentRequest studentRequest) {
//        StudentResponse updatedStudent = studentService.updateStudentByStudentNumber(studentNumber, studentRequest);
//        return ResponseEntity.ok(updatedStudent);
//    }
//
//
//}


package com.cagri.studentapi.controller;

import com.cagri.studentapi.dto.StudentRequest;
import com.cagri.studentapi.dto.StudentResponse;
import com.cagri.studentapi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        StudentResponse savedStudent = studentService.saveStudent(studentRequest);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") long id, @RequestBody StudentRequest studentRequest) {
        StudentResponse updatedStudent = studentService.updateStudent(id, studentRequest);
        return updatedStudent != null ? new ResponseEntity<>(updatedStudent, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update a student by student number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @PutMapping("/studentNumber/{studentNumber}")
    public ResponseEntity<StudentResponse> updateStudentByStudentNumber(@PathVariable("studentNumber") String studentNumber, @Valid @RequestBody StudentRequest studentRequest) {
        StudentResponse updatedStudent = studentService.updateStudentByStudentNumber(studentNumber, studentRequest);
        return ResponseEntity.ok(updatedStudent);
    }
}
