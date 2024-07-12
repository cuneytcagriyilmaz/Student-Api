

package com.cagri.studentapi.controller;

import com.cagri.studentapi.dto.GradeRequest;
import com.cagri.studentapi.dto.GradeResponse;
import com.cagri.studentapi.service.GradeService;
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
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Operation(summary = "Add a new grade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Grade added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GradeResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<GradeResponse> addGrade(@RequestBody GradeRequest gradeRequest) {
        GradeResponse savedGrade = gradeService.addGrade(gradeRequest);
        return savedGrade != null ? new ResponseEntity<>(savedGrade, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Update a grade by student number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grade updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GradeResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Grade not found",
                    content = @Content)})
    @PutMapping("/{studentNumber}")
    public ResponseEntity<GradeResponse> updateGrade(@PathVariable String studentNumber, @Valid @RequestBody GradeRequest gradeRequest) {
        GradeResponse updatedGrade = gradeService.updateGrade(studentNumber, gradeRequest);
        return new ResponseEntity<>(updatedGrade, HttpStatus.OK);
    }
}
