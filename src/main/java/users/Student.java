package users;

import programCourse.CourseFull;
import programCourse.CourseYear;
import timetables.TimeSlot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


/** This clas is for students,
 * It inherits name and year from user class
 */
public class Student extends User {
    CourseFull courseFull;
    CourseYear courseYear;
    List<TimeSlot> classTimes = new ArrayList<>();

    public Student(){
        super("","");
    }

    /**
     * @param userId
     * @param name
     * @param courseCode
     * @param year
     */
    // Constructor with just base params of User
    public Student(String userId, String name, String courseCode, String year) {
        super(userId, name);
        if(!CourseFull.checkCode(courseCode)){
            throw new IllegalArgumentException("Course Doesnt Exist, it must be made first");
        }
        this.courseFull = CourseFull.retrieveCourseFromCode(courseCode);
        if(!this.courseFull.checkYear(year)){
            throw new IllegalArgumentException("Course Year Doesnt Exist, it must be made first");
        }
        this.courseYear = courseFull.retrieveCourseYearFromCode(year);
        classTimes = new ArrayList<>();
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }
    public CourseYear getCourseYear(){
        return this.courseYear;
    }
    public void setCourseFull(CourseFull courseFull){
        this.courseFull = courseFull;
    }
    public CourseFull getCourseFull(){
        return this.courseFull;
    }

    public String toString(){
        return "Name: " + name + " ID: " + userId;
    }

}
