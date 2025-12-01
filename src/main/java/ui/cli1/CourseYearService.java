package ui.cli1;

/**
 * This interface is used by InMemoryCourseYearService
 */
public interface CourseYearService {

    /**
     * Adds a year to a courseFull object
     * @param courseCode code of a course
     * @param year year of a course
     * @return true if the year was added successfully to the course, false if either
     *         the given course code does not exist or if the given year has already
     *         been added to this course
     */
    public boolean addYear(String courseCode, String year);
}
