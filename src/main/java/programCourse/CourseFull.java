package programCourse;
import users.Student;

import java.util.*;

/**
 * Represents an entire academic course (degree) such as
 * BSc Computer Science or BA Business.
 *
 * A CourseFull contains:
 *  - A course code (e.g., "LM051")
 *  - A human-readable course name
 *  - A static map to check if a courseFull already exist by a certain code (so two LM121 cant be made for example)
 *  - A non-static map for yearCodeMapping to check if a courseYear object already exists
 *  - This map yearCodeMapping will also be used as the list to find referenced CourseYears to a CourseFull
 *
 * This class acts as the top-level container for course structure.
 */
public class CourseFull {
    private final String courseCode;
    private final String name;
    private static final Map<String, CourseFull> courseCodeMapping = new HashMap<>();//Changed by Yousef 18-11 9:00 to make sure that there won't ba any data leaks.
    private final Map<String, CourseYear> yearCodeMapping;

    //private Map<Integer, CourseYear> yearCodeMapping;// Suggestion

    /**
     * Constructs a CourseFull object by assigning the course code,
     * name, and list of academic years that make up the course.
     *
     * @param courseCode  the course identifier (e.g. "BSC-CS")
     * @param name  the course name displayed to users
     *
     * @throws IllegalArgumentException if any argument is null
     */
    public CourseFull(String courseCode, String name) {
        // Validate inputs
        if (courseCode == null || name == null)
            throw new IllegalArgumentException("Parameters can't be null");

        if (courseCodeMapping.containsKey(courseCode))//Added by Yousef 18-11 9:00 to make sure that no one can overwrite this on accident.
        {
            throw new IllegalStateException("Course code alreay exists \n" + courseCodeMapping.get(courseCode));
        }
        yearCodeMapping = new HashMap<>();
        this.courseCode = courseCode;
        this.name = name;
        courseCodeMapping.put(courseCode, this);
    }
    // creates class of course year and adds it to list
    public void addCourseYear(String year){
        if(year == null || year.isEmpty())//18-11 Yousef 9:00
        {
            throw new IllegalArgumentException("Year can't be null");
        }
        if(yearCodeMapping.containsKey(year))//18-11 Yousef 9:00
        {
            throw new IllegalStateException("year already exists for key "+ year);
        }
        CourseYear courseYearTemp = new CourseYear(this, year);
        yearCodeMapping.put(year, courseYearTemp);
    }
    // Retrieve list of Course Years
    public List<CourseYear> getCourseYears(){
        return new ArrayList<>(yearCodeMapping.values());
    }
    // checks if a course code already exists
    public static boolean checkCode(String code){
        return courseCodeMapping.containsKey(code);
    }
    // returns course object associated with code
    public static CourseFull retrieveCourseFromCode(String code){
        return courseCodeMapping.get(code);
    }
    // checks if a course year already exists, yearKey is just a string on its own "1", "2", etc
    public boolean checkYear(String yearKey){
        return yearCodeMapping.containsKey(yearKey);
    }
    // returns courseYear object associated with year, yearKey is just a string on its own "1", "2", etc
    public CourseYear retrieveCourseYearFromCode(String yearKey){
        return yearCodeMapping.get(yearKey);
    }

    public String getCode(){
        return this.courseCode;
    }
    public String getName(){
        return this.name;
    }

    /** hashing for sets, might not use */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseFull that = (CourseFull) o;
        return this.getCode().equals(that.getCode());
    }
    @Override
    public int hashCode(){
        return this.getCode().hashCode();
    }
}
