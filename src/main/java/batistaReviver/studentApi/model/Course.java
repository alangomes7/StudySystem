package batistaReviver.studentApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a course offered by the institution.
 *
 * This entity is mapped to the "courses" table in the database. It contains information
 * about the course name, description, and the different classes (StudyClass) offered
 * for this course.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    /**
     * The unique identifier for the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the course (e.g., "Introduction to Computer Science").
     * This field cannot be null and must be unique.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * A brief description of the course content.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * The set of specific class offerings for this course across different years and semesters.
     * This establishes a one-to-many relationship with the StudyClass entity.
     */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<StudyClass> studyClasses = new HashSet<>();

    /**
     * Constructs a new Course instance without an ID.
     *
     * @param name The name of the course.
     * @param description A description of the course.
     */
    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

