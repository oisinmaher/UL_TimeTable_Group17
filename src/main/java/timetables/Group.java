package timetables;

import users.Student;
import java.util.List;


public class Group {

    private int year ;
    private String groupId;
    private List<Student> students;
    private String courseCode;

    public Group(int year, String groupId, List<Student> students, String courseCode) {

        this.year = year;
        this.students = students;
        this.groupId = groupId;
        this.courseCode = courseCode;

    }

    public String getGroupId() {
        return groupId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getYear() {
        return year;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {

        return "Group: " + groupId + "Year: " + year + " Course: " + courseCode;
    }

}
