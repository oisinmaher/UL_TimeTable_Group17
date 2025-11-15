package src.main.java.users;

import src.main.java.programCourse.ProgramSemester;
import src.main.java.programCourse.ProgramYear;
import src.main.java.timetables.TimeTable;


/** This clas is for students,
*  It inherits the User class
*  It will hold the following info
*  Student name/ id
*  Program Students enrolled in
*  The students timetable
*  Admins will use this object to change information on student (e.g TimeTable and yearOfStudy)
 */
public class Student extends User {
    ProgramSemester programSemester;
    ProgramYear programYear;
    TimeTable timeTable;
    int yearOfStudy;

    /**
     * @param name
     * @param userId
     */
    // Constructor with just base params of User
    public Student(String name, int userId) {
        super(name, userId);
        timeTable = new TimeTable(this);
    }

    // Constructor with all info
    public Student(String name, int userId, int yearOfStudy, ProgramSemester programSemester, ProgramYear programYear) {
        super(name, userId);
        this.programSemester = programSemester;
        this.programYear = programYear;
        this.yearOfStudy = yearOfStudy;
        // passes in the instantiation of this object (current student object)
        timeTable = new TimeTable(this);
    }

    public void setProgramSemester(ProgramSemester programSemester) {
        this.programSemester = programSemester;
    }

    public void setProgramYear(ProgramYear programYear) {
        this.programYear = programYear;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * @return
     */
    public ProgramSemester getProgramSemester(){
        return this.programSemester;
    }
    public ProgramYear getProgramYear(){
        return this.programYear;
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
