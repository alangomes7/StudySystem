package batistaReviver.studentApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a specific offering of a Course in a given year and semester.
 *
 * This entity is mapped to the "study_classes" table and acts as a bridge between
 * courses, professors, and students. It defines a concrete class that students can
 * subscribe to.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "study_classes")
public class StudyClass {

    /**
     * The unique identifier for the class offering.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The academic year in which the class is offered.
     */
    @Column(nullable = false)
    private int year;

    /**
     * The semester in which the class is offered (e.g., 1 for the first semester, 2 for the second).
     */
    @Column(nullable = false)
    private int semester;

    /**
     * The course that this class is an offering of.
     * Establishes a many-to-one relationship with the Course entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /**
     * The professor teaching this specific class.
     * Establishes a many-to-one relationship with the Professor entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    /**
     * The set of student subscriptions for this specific class.
     * Establishes a one-to-many relationship with the Subscription entity.
     */
    @OneToMany(mappedBy = "studyClass", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Subscription> subscriptions = new HashSet<>();


    /**
     * Constructs a new StudyClass instance.
     *
     * @param year      The academic year.
     * @param semester  The semester.
     * @param course    The course being offered.
     * @param professor The professor teaching the class.
     */
    public StudyClass(int year, int semester, Course course, Professor professor) {
        this.year = year;
        this.semester = semester;
        this.course = course;
        this.professor = professor;
    }
}
