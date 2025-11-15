package src.main.java.users;

import src.main.java.timetables.ProgramWithModule;
import src.main.java.timetables.TimeTable;

import java.util.ArrayList;
import java.util.*;


/** This clas is for students,
*  It inherits the User class
*  It will hold the following info
*  Student name/ id
*  Program Students enrolled in
*  The students timetable
*  Admins will use this object to change information on student (e.g TimeTable and yearOfStudy)
 */
public class Student extends User {
    ProgramWithModule program;
    TimeTable timeTable;
    int year;

    /**
     * @param name
     * @param userId
     */
    // Constructor with just base params of User
    public Student(String name, int userId) {
        super(name, userId);
        timeTable = new TimeTable();
    }

    public Student(String name, int userId, ProgramWithModule program) {
        super(name, userId);
        this.program = program;
        timeTable = new TimeTable();
    }

    public void setProgram(ProgramWithModule program) {
        this.program = program;
    }

    public void setYear(int year) {
        this.year = year;
    }


    /**
     * @return
     */
    public ProgramWithModule getProgram(){
        return this.program;
    }

    /**
     * @return
     */
    public String toString(){
        return "Name: " + name + " ID: " + userId;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }


}
