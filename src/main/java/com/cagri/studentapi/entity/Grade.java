package com.cagri.studentapi.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "grade", schema = "zyfera")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private long id;

    @Column(name = "code")
    @Size(max = 50, message = "Code cannot exceed 50 characters")
    private String code;

    @Column(name = "value")
    @NotNull(message = "Value cannot be null")
    @Min(value = 0, message = "Value must be at least 0")
    @Max(value = 100, message = "Value must be at most 100")
    private int value;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;
}
