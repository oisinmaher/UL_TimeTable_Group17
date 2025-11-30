package users;

import programCourse.CourseFull;
import programCourse.CourseYear;
import timetables.TimeSlot;
import timetables.UserTimeTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** This clas is for students,
 * It inherits name and year from user class
 */
public class Student extends User {
    CourseFull courseFull;
    CourseYear courseYear;
    List<TimeSlot> classTimes = new ArrayList<>();
    private static Map<String, Student> allStudentsEnrolled = new HashMap<>();
    /**
     * @param userId
     * @param name
     * @param courseCode
     * @param year
     */
    // Constructor with just base params of User
    public Student(String userId, String name, String courseCode, String year) {
        super(userId, name);
        if(!CourseFull.containsCode(courseCode)){
            throw new IllegalArgumentException("Course Doesnt Exist, it must be made first");
        }
        this.courseFull = CourseFull.getCourseFromCode(courseCode);
        if(!this.courseFull.containsYear(year)){
            throw new IllegalArgumentException("Course Year Doesnt Exist, it must be made first");
        }
        this.courseYear = courseFull.getCourseYear(year);
        classTimes = new ArrayList<>();
        allStudentsEnrolled.put(userId, this);
    }
    public static boolean containsStudentId(String studentId){
        return allStudentsEnrolled.containsKey(studentId);
    }
    public static Student getStudentFromId(String studentId){
        if(containsStudentId(studentId))
            return allStudentsEnrolled.get(studentId);
        else throw new IllegalArgumentException("Student ID: " + studentId + " does not exist");
    }

    public static Map<String, Student> getAllStudentsEnrolled(){
        return allStudentsEnrolled;
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

    public UserTimeTable getUserTimeTable() {
        if (userTimeTable == null) {
            userTimeTable = new UserTimeTable(this);
        }
        return userTimeTable;
    }

    public String toString(){
        return "Name: " + name + " ID: " + userId;
    }

}
