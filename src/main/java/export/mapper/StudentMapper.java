package export.mapper;

import export.dto.StudentCsvDto;
import programCourse.CourseFull;
import users.Student;

/**
 *
 */
public class StudentMapper implements MapperInterface{
    /**
     * Takes Student DTOs and makes them into a flat CSV exportable object, static as it only
     * takes in a Student object
     * @param s the DTO to be mapped
     * @return a flattened DTO
     */
    public static StudentCsvDto flatten(Student s) {
        return new StudentCsvDto(
                s.getUserId(),
                s.getName(),
                s.getCourseFull().getCode(),
                s.getCourseYear().getYearNumber()
        );

    }

    /**
     * Takes a flat student DTO and makes it into a student object
     * @param s the student sto to inflate
     * @return a student object
     */
    public static Student inflate(StudentCsvDto s) {

        if (!CourseFull.containsCode(s.getCourseCode())) {
            throw new IllegalStateException(
                    "Cannot inflate Student: CourseFull " + s.getCourseCode() + " does not exist."
            );
        }

        CourseFull course = CourseFull.getCourseFromCode(s.getCourseCode());

        if (!course.containsYear(s.getYear())) {
            throw new IllegalStateException(
                    "Cannot inflate Student: CourseYear " + s.getYear() + " does not exist for course " + s.getCourseCode()
            );
        }
        return new Student(
                    s.getUserId(),
                    s.getName(),
                    s.getCourseCode(),
                    s.getYear()
            );
    }
}

