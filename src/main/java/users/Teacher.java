package users;

import programCourse.CourseFull;

import java.util.*;

/** This is the Teacher class
 *  This class inherits User
 *  It will be quite similar to Student but with modules from different courses
 */
public class Teacher extends User {

    public static Map<String, Teacher> allTeachers = new HashMap<>();
    // List of courses the teacher teaches (e.g LM121, LM051 etc.)
    List<CourseFull> courseTaught;

    // Constructor with base parameters of user
    public Teacher(String teacherId, String name) {
        super(teacherId, name);
        courseTaught = new ArrayList<>();
        allTeachers.put(this.userId, this);
    }
    public String getTeacherId(){
        return this.userId;
    }
    public static boolean checkTeacherId(String teacherId) {
        return allTeachers.containsKey(teacherId);
    }

    public static Teacher getTeacherFromId(String teacherId) {
        if(checkTeacherId(teacherId))
            return allTeachers.get(teacherId);
        else throw new IllegalArgumentException("This Teacher ID " + teacherId + " does not exist");
    }
}
