package batistaReviver.studentApi.dto;

import batistaReviver.studentApi.model.StudyClass;

/**
 * DTO for representing a {@link StudyClass}. This object is used to transfer class data between the
 * service and the client, avoiding issues with lazy loading.
 *
 * @param id The class ID.
 * @param classCode The unique code for the class.
 * @param year The academic year.
 * @param semester The semester.
 * @param courseId The ID of the course.
 * @param courseName The name of the course.
 * @param professorId The ID of the professor (can be null).
 * @param professorName The name of the professor (can be null).
 */
public record StudyClassDto(
    Long id,
    String classCode,
    int year,
    int semester,
    Long courseId,
    String courseName,
    Long professorId,
    String professorName) {
  public StudyClassDto(StudyClass studyClass) {
    this(
        studyClass.getId(),
        studyClass.getClassCode(),
        studyClass.getYear(),
        studyClass.getSemester(),
        studyClass.getCourse().getId(),
        studyClass.getCourse().getName(),
        studyClass.getProfessor() != null ? studyClass.getProfessor().getId() : null,
        studyClass.getProfessor() != null ? studyClass.getProfessor().getName() : null);
  }
}
