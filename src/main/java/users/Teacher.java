package src.main.java.users;

import src.main.java.programCourse.ProgramWithModule;
import src.main.java.timetables.TimeTable;

import java.util.*;

/** This is the Teacher class
 *  This class inherits User
 *  It will be quiet similiar to Student but with modules from different courses
 */
public class Teacher extends User {
    // Timetable object
    TimeTable timeTable;

    // List of courses the teacher teaches (e.g LM121, LM051 etc.)
    List<ProgramWithModule> programsTaught;



    // Constructor with base parameters of user
    public Teacher(String name, int userId) {
        super(name, userId);
        this.timeTable = new TimeTable(this);
        programsTaught = new ArrayList<>();
    }

    // Constructor but with a pre-made list of Programs (Likely won't use)
    public Teacher(String name, int userId, List<ProgramWithModule> programsTaught) {
        super(name, userId);
        this.timeTable = new TimeTable();
        this.programsTaught = programsTaught;
    }

    // Returns timetable
    public TimeTable getTimeTable() {
        return timeTable;
    }
}
