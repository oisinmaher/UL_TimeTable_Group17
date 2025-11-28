package users;

import programCourse.CourseFull;
import programCourse.CourseYear;
import timetables.TimeSlot;
import timetables.UserTimeTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** This class is for students.
 *  It inherits name and year from user class
 *  Contains:
 *      - Map of user ID to Student object (allStudentsEnrolled)
 */
public class Student extends User {
    CourseFull courseFull;
    CourseYear courseYear;
    List<TimeSlot> classTimes = new ArrayList<>();
    private static Map<String, Student> allStudentsEnrolled = new HashMap<>();

    /**
     * Constructor with base parameters of user.
     *
     * @param userId users ID number
     * @param name name of the student
     * @param courseCode code associated with a course
     * @param year the year of study that the student is in
     * @throws IllegalArgumentException if a course with the given course code can't be found
     * @throws IllegalArgumentException if a course with the given year can't be found
     */
    public Student(String userId, String name, String courseCode, String year) {
        super(userId, name);
        if(!CourseFull.containsCode(courseCode)){
            throw new IllegalArgumentException("Course doesn't Exist, it must be made first");
        }
        this.courseFull = CourseFull.getCourseFromCode(courseCode);
        if(!this.courseFull.containsYear(year)){
            throw new IllegalArgumentException("Course Year doesn't Exist, it must be made first");
        }
        this.courseYear = courseFull.getCourseYear(year);
        classTimes = new ArrayList<>();
        allStudentsEnrolled.put(userId, this);
    }

    /**
     * Checks if the given student ID has a corresponding Student object
     * @param studentId student ID number
     * @return true if the given student ID has a student, false otherwise
     */
    public static boolean containsStudentId(String studentId){
        return allStudentsEnrolled.containsKey(studentId);
    }

    /**
     * Gets the Student object with the given student ID
     * @param studentId student ID number
     * @return Student object
     * @throws IllegalArgumentException if the given student ID doesn't exist
     */
    public static Student getStudentFromId(String studentId){
        if(containsStudentId(studentId))
            return allStudentsEnrolled.get(studentId);
        else throw new IllegalArgumentException("Student ID: " + studentId + " does not exist");
    }

    /**
     * Sets the year that the student is in
     * @param courseYear year of study of a course
     */
    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }

    /**
     * Gets the year that the student is in, as a CourseYear object
     * @return CourseYear object
     */
    public CourseYear getCourseYear(){
        return this.courseYear;
    }

    /**
     * Sets the course that the student is in
     * @param courseFull a university course
     */
    public void setCourseFull(CourseFull courseFull){
        this.courseFull = courseFull;
    }

    /**
     * Gets the course that the student is in
     * @return CourseFull object
     */
    public CourseFull getCourseFull(){
        return this.courseFull;
    }

    /**
     * toString of Students name and ID
     * @return Student name and ID
     */
    public String toString(){
        return "Name: " + name + " ID: " + userId;
    }
}
