package com.cagri.studentapi.repository;

import com.cagri.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentByStudentNumber(String studentNumber);
    boolean existsByStudentNumber(String studentNumber);

}
