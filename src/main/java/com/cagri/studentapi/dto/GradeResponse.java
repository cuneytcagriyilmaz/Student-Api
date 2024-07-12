package com.cagri.studentapi.dto;

import com.cagri.studentapi.entity.Grade;

import java.util.List;

public record GradeResponse(String name, String surname, String studentNumber, List<Grade> grades) {
}
