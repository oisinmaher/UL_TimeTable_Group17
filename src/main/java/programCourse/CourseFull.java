package programCourse;
import java.util.*;

/**
 * Represents an entire academic course (degree) such as
 * BSc Computer Science or BA Business.
 *
 * A CourseFull contains:
 *  - A course code (e.g., "BSC-CS")
 *  - A human-readable course name
 *  - A list of courseYear objects that describe the structure of the degree
 *
 * This class acts as the top-level container for course structure.
 */
public class CourseFull {

    private String code;
    private String name;
    private List<CourseYear> years;

    /**
     * Constructs a CourseFull object by assigning the course code,
     * name, and list of academic years that make up the course.
     *
     * @param code  the course identifier (e.g. "BSC-CS")
     * @param name  the course name displayed to users
     * @param years the list of CourseYear objects representing each academic year
     *
     * @throws IllegalArgumentException if any argument is null
     */
    public CourseFull(String code, String name, List<CourseYear> years) {

        // Validate inputs
        if (code == null) {
            throw new IllegalArgumentException("Code can't be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        if (years == null) {
            throw new IllegalArgumentException("Years can't be null");
        }

        this.code = code;
        this.name = name;

        // Store a defensive copy of the list of years
        this.years = new ArrayList<>(years);
    }

    // You can add getters or course behaviour methods later if needed.
}
