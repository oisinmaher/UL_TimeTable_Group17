package users;

import programCourse.CourseFull;
import timetables.TimeTable;

import java.util.*;

/** This is the Teacher class
 *  This class inherits User
 *  It will be quiet similiar to Student but with modules from different courses
 */
public class Teacher extends User {
    // Timetable Object
    TimeTable timeTable;

    // List of courses the teacher teaches (e.g LM121, LM051 etc.)
    List<CourseFull> courseTaught;

    // Constructor with base parameters of user
    public Teacher(String userId, String name) {
        super(userId, name);
        this.timeTable = new TimeTable(this);
        courseTaught = new ArrayList<>();
    }

    // Constructor but with a pre-made list of Programs (Likely won't use)
    public Teacher(String name, String userId, List<CourseFull> programsTaught) {
        super(userId, name);
        this.timeTable = new TimeTable(this);
        this.courseTaught = courseTaught;
    }

    // Returns timetable
    public TimeTable getTimeTable() {
        return timeTable;
    }
}
