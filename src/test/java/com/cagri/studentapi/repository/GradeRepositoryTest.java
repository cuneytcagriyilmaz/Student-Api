package com.cagri.studentapi.repository;

import com.cagri.studentapi.entity.Grade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class GradeRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;

    private Long testGradeId;

    @BeforeEach
    void setUp() {
        Grade grade = new Grade();
        grade.setCode("ENG101");
        grade.setValue(85);

        Grade savedGrade = gradeRepository.save(grade);
        testGradeId = savedGrade.getId();
    }

    @AfterEach
    void tearDown() {
        gradeRepository.deleteById(testGradeId);
    }

    @Test
    void testSaveGrade() {
        Grade grade = new Grade();
        grade.setCode("MAT102");
        grade.setValue(90);

        Grade savedGrade = gradeRepository.save(grade);

        Optional<Grade> retrievedGrade = gradeRepository.findById(savedGrade.getId());
        assertTrue(retrievedGrade.isPresent());
        assertEquals("MAT102", retrievedGrade.get().getCode());
        assertEquals(90, retrievedGrade.get().getValue());
    }

    @Test
    void testUpdateGrade() {
        Optional<Grade> optionalGrade = gradeRepository.findById(testGradeId);
        assertTrue(optionalGrade.isPresent());

        Grade grade = optionalGrade.get();
        grade.setCode("PHY103");
        grade.setValue(88);

        Grade updatedGrade = gradeRepository.save(grade);

        assertEquals("PHY103", updatedGrade.getCode());
        assertEquals(88, updatedGrade.getValue());
    }

}