package com.cagri.studentapi.repository;

import com.cagri.studentapi.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private void createStudent(String studentNumber) {
        Student student = new Student();
        student.setStudentNumber(studentNumber);
        student.setName("Cagri");
        student.setSurname("Yilmaz");
        studentRepository.save(student);
    }

    @BeforeEach
    void setUp() {
        createStudent("B018X00018");
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void findStudentByStudentNumber() {
        Student student = studentRepository.findStudentByStudentNumber("B018X00018");
        assertNotNull(student);
        assertEquals("Cagri", student.getName());
        assertEquals("Yilmaz", student.getSurname());
    }

    @Test
    void existsByStudentNumber() {
        assertTrue(studentRepository.existsByStudentNumber("B018X00018"));
        assertFalse(studentRepository.existsByStudentNumber("nonexistentStudentNumber"));
    }
}