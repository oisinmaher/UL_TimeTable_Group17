package programCourse;

import java.util.*;

/**
 * Represents an entire academic course (degree) such as
 * B.Sc. Computer Science or BA Business.

 * A CourseFull contains:
 *  - A course code (e.g., "LM051")
 *  - A human-readable course name
 *  - A static map to check if a courseFull already exist by a certain code (so two LM121 cant be made for example)
 *  - A non-static map for yearCodeMapping to check if a courseYear object already exists
 *  - This map yearCodeMapping will also be used as the list to find referenced CourseYears to a CourseFull

 * This class acts as the top-level container for course structure.
 */
public class CourseFull {
    private final String courseCode;
    private final String name;
    private static final Map<String, CourseFull> courseCodeMapping = new HashMap<>();//Changed by Yousef 18-11 9:00 to make sure that there won't ba any data leaks.
    private final Map<String, CourseYear> yearCodeMapping;

    /**
     * Constructs a CourseFull object by assigning the course code,
     * name, and list of academic years that make up the course.
     *
     * @param courseCode  the course identifier (e.g. "LM121")
     * @param name  the course name displayed to users ("computer science")
     *
     * @throws IllegalArgumentException if any argument is null
     */
    public CourseFull(String courseCode, String name) {
        // Validate inputs
        if (courseCode == null || courseCode.isEmpty() || name == null || name.isEmpty())
            throw new IllegalArgumentException("Parameters can't be null/empty");
        courseCode = courseCode.toLowerCase();
        if (courseCodeMapping.containsKey(courseCode))//Added by Yousef 18-11 9:00 to make sure that no one can overwrite this on accident.
        {
            throw new IllegalStateException("Course code alreay exists \n" + courseCodeMapping.get(courseCode));
        }
        yearCodeMapping = new HashMap<>();
        this.courseCode = courseCode;
        this.name = capitalizeName(name);
        courseCodeMapping.put(courseCode, this);
    }

    /**
     * Makes first letter of every word a capital letter and the rest lowercase
     * @param name capitalise name of a course
     * @return capitalised version
     */
    private String capitalizeName(String name) {
        char[] lowerCase = name.toLowerCase().toCharArray();
        char asciiDif = 'a' - 'A';
        lowerCase[0] -= asciiDif;
        for(int i = 1; i < lowerCase.length; i++){
            if(lowerCase[i-1] == ' ')
                lowerCase[i] -= asciiDif;
        }
        return new String(lowerCase);
    }

    /**
     * creates a courseYear and adds it to this object's yearCodeMapping map
     * @param year the course's year ("1", "2")
     */
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

    /**
     * Creates and returns a list of course years associated with this course
     * @return list of course years
     */
    public List<CourseYear> getCourseYears(){
        return new ArrayList<>(yearCodeMapping.values());
    }

    /**
     * check if a course code already exists amongst all course objects
     * @param code the course's code (e.g "LM121")
     * @return boolean value, true if it exists, false otherwise
     */
    public static boolean containsCode(String code){
        return courseCodeMapping.containsKey(code.toLowerCase());
    }

    /**
     *  returns course object associated with code
     * @param code the course's code (e.g "LM121")
     * @return a CourseFull object
     */
    public static CourseFull getCourseFromCode(String code){
        return courseCodeMapping.get(code.toLowerCase());
    }

    /**
     * checks if a course year already exists
     * @param yearKey yearKey is just a string on its own "1", "2", etc
     * @return boolean value; true if it exists, false otherwise
     */
    public boolean containsYear(String yearKey){
        return yearCodeMapping.containsKey(yearKey);
    }

    /**
     * returns courseYear object associated with year
     * @param yearKey earKey is just a string on its own "1", "2", etc
     * @return courseYear object
     */
    public CourseYear getCourseYear(String yearKey){
        return yearCodeMapping.get(yearKey);
    }

    /**
     * Returns a list of all courses code and name
     * @return list of course code and names
     */
    public static List<String> getAllCourses(){
        List<String> list = new ArrayList<>();
        for(Map.Entry<String, CourseFull> entry : courseCodeMapping.entrySet()){
            list.add("Code: " + entry.getKey() + ", Name: " + entry.getValue().getName());
        }
        return list;
    }

    /**
     * gets the code associated with this course object
     * @return a string of course code (e.g "LM121")
     */
    public String getCode(){
        return this.courseCode;
    }

    /**
     * gets the name associated with this course object
     * @return a string of course name (e.g "Computer Science")
     */
    public String getName(){
        return this.name;
    }
}
