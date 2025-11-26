package users;

import modules.CourseModule;

import java.util.*;

/** This is the Teacher class
 *  This class inherits User
 *  It will be quite similar to Student but with modules from different courses
 */
public class Teacher extends User {

    private final static Map<String, Teacher> allTeachers = new HashMap<>();
    // List of courses the teacher teaches (e.g LM121, LM051 etc.)
     private final Map<String, CourseModule> coursesTaught;

    // Constructor with base parameters of user
    public Teacher(String teacherId, String name) {
        super(teacherId, name);
        coursesTaught = new HashMap<>();
        allTeachers.put(this.userId, this);
    }

    public void addCourseToTeacher(String courseCode){
        courseCode = courseCode.toLowerCase();
        CourseModule courseModule = CourseModule.getModuleFromCode(courseCode);
        coursesTaught.put(courseCode, courseModule);
    }
    public void removeCourseFromTeacher(String courseCode){
        courseCode = courseCode.toLowerCase();
        if(containsCourse(courseCode)){
            coursesTaught.remove(courseCode);
        }
        else{
            throw new IllegalArgumentException("Teacher doesnt teach this course");
        }
    }
    private boolean containsCourse(String courseCode){
        return coursesTaught.containsKey(courseCode);
    }
    private static boolean checkTeacherId(String teacherId) {
        return allTeachers.containsKey(teacherId);
    }
    public static Teacher getTeacherFromId(String teacherId) {
        if(checkTeacherId(teacherId))
            return allTeachers.get(teacherId);
        else throw new IllegalArgumentException("This Teacher ID " + teacherId + " does not exist");
    }
    @Override
    public String toString(){
        return getName();
    }
}
