package com.cagri.studentapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student", schema = "zyfera")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private long id;

    @Column(name = "name")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @Column(name = "surname")
    @Size(max = 255, message = "Surname cannot exceed 255 characters")
    private String surname;

    @Column(name = "student_number", unique = true)
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Student number must contain only alphanumeric characters")
    @Size(max = 30, message = "Student number cannot exceed 20 characters")
    private String studentNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Grade> gradeList = new ArrayList<>();


}
