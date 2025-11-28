package users;

import modules.CourseModule;

import java.util.*;

/** Teacher class.
 *  This class inherits User
 *  It will be quite similar to Student but with modules from different courses
 *  Contains:
 *      - Map
 */
public class Teacher extends User {

    private final static Map<String, Teacher> allTeachers = new HashMap<>();
    // List of courses the teacher teaches (e.g. LM121, LM051 etc.)
    private final Map<String, CourseModule> coursesTaught;

    /**
     * Constructor with base parameters of user
     * @param teacherId teachers ID number
     * @param name name of the teacher
     */
    public Teacher(String teacherId, String name) {
        super(teacherId, name);
        coursesTaught = new HashMap<>();
        allTeachers.put(this.userId, this);
    }

    /**
     * Adds modules to a map of modules that this teacher teaches
     * @param courseCode code of a university course
     */
    public void addCourseToTeacher(String courseCode){
        courseCode = courseCode.toLowerCase();
        CourseModule courseModule = CourseModule.getModuleFromCode(courseCode);
        coursesTaught.put(courseCode, courseModule);
    }

    /**
     *
     * @param courseCode
     */
    public void removeCourseFromTeacher(String courseCode){
        courseCode = courseCode.toLowerCase();
        if(containsCourse(courseCode)){
            coursesTaught.remove(courseCode);
        }
        else{
            throw new IllegalArgumentException("Teacher doesn't teach this course");
        }
    }

    /**
     *
     * @param courseCode
     * @return
     */
    private boolean containsCourse(String courseCode){
        return coursesTaught.containsKey(courseCode);
    }

    /**
     * Checks if the given teacher ID exists
     * @param teacherId teachers ID number
     * @return true if the teacher ID exists, false otherwise
     */
    private static boolean checkTeacherId(String teacherId) {
        return allTeachers.containsKey(teacherId);
    }

    /**
     * Gets the Teacher object if the given teacher ID exists
     * @param teacherId teachers ID number
     * @return Teacher object
     * @throws IllegalArgumentException if the teacher ID doesn't exist
     */
    public static Teacher getTeacherFromId(String teacherId) {
        if(checkTeacherId(teacherId))
            return allTeachers.get(teacherId);
        else throw new IllegalArgumentException("This Teacher ID " + teacherId + " does not exist");
    }

    /**
     * toString of the teachers name
     * @return name of the teacher
     */
    @Override
    public String toString(){
        return getName();
    }
}
