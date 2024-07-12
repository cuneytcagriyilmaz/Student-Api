package com.cagri.studentapi.service;

import com.cagri.studentapi.dto.GradeRequest;
import com.cagri.studentapi.dto.GradeResponse;

public interface GradeService {



    GradeResponse addGrade(GradeRequest gradeRequest);
    GradeResponse updateGrade(String studentNumber, GradeRequest gradeRequest);


 }
