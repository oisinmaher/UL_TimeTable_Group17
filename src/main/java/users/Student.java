package src.main.java.users;

import src.main.java.programCourse.CourseSemester;
import src.main.java.programCourse.CourseYear;
import src.main.java.timetables.TimeTable;


/** This clas is for students,
*  It inherits the User class
*  It will hold the following info
*  Student name/ id
*  course Students enrolled in
*  The students timetable
*  Admins will use this object to change information on student (e.g TimeTable and yearOfStudy)
 */
public class Student extends User {
    CourseSemester courseSemester;
    CourseYear courseYear;
    TimeTable timeTable;
    int yearOfStudy;

    /**
     * @param name
     * @param userId
     */
    // Constructor with just base params of User
    public Student(String userId, String name) {
        super(userId, name);
        timeTable = new TimeTable(this);
    }

    // Constructor with all info
    public Student(String name, String userId, int yearOfStudy, CourseSemester courseSemester, CourseYear courseYear) {
        super(userId, name);
        this.courseSemester = courseSemester;
        this.courseYear = courseYear;
        this.yearOfStudy = yearOfStudy;
        // passes in the instantiation of this object (current student object)
        timeTable = new TimeTable(this);
    }

    public void setCourseSemester(CourseSemester courseSemester) {
        this.courseSemester = courseSemester;
    }

    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * @return
     */
    public CourseSemester getCourseSemester(){
        return this.courseSemester;
    }
    public CourseYear getCourseYear(){
        return this.courseYear;
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

    public void setName(String name){
        this.name = name;
    }


}
