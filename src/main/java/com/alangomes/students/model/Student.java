package com.alangomes.students.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Represents a student entity in the database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The unique identifier for the student.
     */
    private Long id;

    @Column(nullable = false)
    /**
     * The name of the student.
     */
    private String name;

    @Column(nullable = false, unique = true)
    /**
     * The registration number of the student, which must be unique.
     */
    private String register;

    @Column(nullable = false, unique = true)
    /**
     * The email address of the student, which must be unique.
     */
    private String email;

    /**
     * Constructor to create a new Student instance without an ID.
     * @param email The student's email.
     * @param register The student's registration number.
     * @param name The student's name.
     */
    public Student(String email, String register, String name) {
        this.email = email;
        this.register = register;
        this.name = name;
    }
}