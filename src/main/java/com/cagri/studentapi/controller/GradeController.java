package com.cagri.studentapi.controller;

import com.cagri.studentapi.dto.GradeRequest;
import com.cagri.studentapi.dto.GradeResponse;
import com.cagri.studentapi.service.GradeService;
import com.cagri.studentapi.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;


    @PostMapping
    public ResponseEntity<GradeResponse> addGrade(@RequestBody GradeRequest gradeRequest) {
        GradeResponse savedGrade = gradeService.addGrade(gradeRequest);
        return savedGrade != null ? new ResponseEntity<>(savedGrade, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{studentNumber}")
    public ResponseEntity<GradeResponse> updateGrade(@PathVariable String studentNumber, @Valid @RequestBody GradeRequest gradeRequest) {
        GradeResponse updatedGrade = gradeService.updateGrade(studentNumber, gradeRequest);
        return new ResponseEntity<>(updatedGrade, HttpStatus.OK);
    }


}

