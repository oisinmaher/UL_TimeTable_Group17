package export.mapper;

import export.dto.StudentCsvDto;
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
     * WIP
     * @param s
     * @return
     */
    public static Student inflate(StudentCsvDto s) {
            return new Student(null, null, null, null);
    }
}

