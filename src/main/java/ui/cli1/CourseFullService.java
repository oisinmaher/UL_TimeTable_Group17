package ui.cli1;

import java.util.List;

/**
 * This interface is used by InMemoryCourseFullService
 */
public interface CourseFullService {

    /**
     * Creates a university course and return true or false depending on this
     * @param courseCode code of a course
     * @param courseName name of a course
     * @return true if the course was successfully made, false otherwise
     */
    public boolean createCourse(String courseCode, String courseName);

    /**
     * Gets name of a course from a given course code
     * @param courseCode code of a course
     * @return the name of a university course
     */
    public String getNameFromCode(String courseCode);

    /**
     * Gets the list of all course codes and course names
     * @return list of all course codes and course names
     */
    public List<String> listAllCourses();

    /**
     * Gets the list of years of this CourseFull object
     * @param courseCode code of a course
     * @return list of years of this CourseFull object as a string
     */
    public List<String> listCourseYears(String courseCode);
}
