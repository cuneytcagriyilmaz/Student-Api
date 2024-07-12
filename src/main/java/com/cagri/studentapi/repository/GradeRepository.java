package com.cagri.studentapi.repository;

import com.cagri.studentapi.entity.Grade;
import com.cagri.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByStudentAndCode(Student student, String code);

}
