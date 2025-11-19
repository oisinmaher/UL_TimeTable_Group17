package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.programCourse.*;
import src.main.java.rooms.Room;
import src.main.java.users.Student;
import src.main.java.users.Teacher;

import java.util.*;

/**
 * Group will be the students in a given module at a specific semester
 */
public class Group {


    private final CourseModule module;
    private final CourseYear year;
    private final Map<String, Student> studentsInGroup;
    private Teacher teacher;
    private final List<Room> rooms;
    private final Map<String, SubGroup> referencedSubGroups;


    public Group(CourseModule module, CourseYear year){
        if (module == null) {
            throw new IllegalArgumentException("module can't be null");
        }
        if (year == null) {
            throw new IllegalArgumentException("year can't be null");
        }
        this.module = module;
        this.year = year;
        this.teacher = null;
        rooms = new ArrayList<>();
        referencedSubGroups = new HashMap<>();
        studentsInGroup = new HashMap<>();

    }
    public void createSubGroup(){

    }
    public String getYear() {
        return year.getYearNumber();
    }
    public void addStudent(String studentId){
        if(Student.checkStudentId(studentId)){
            studentsInGroup.put(studentId, Student.getStudentFromId(studentId));
        }
        else{
            System.out.println("Student with id " + studentId + " does not exist");
        }
    }
    public void setTeacher(String teacherId){
        if(Teacher.checkTeacherId(teacherId)){
            this.teacher = Teacher.getTeacherFromId(teacherId);
        }
        else{
            System.out.println("Teacher with id " + teacherId + " does not exist");
        }
    }
    public Teacher getTeacherObject() {
        return teacher;
    }
    public String getTeacherName(){
        if(this.teacher == null) return "Teacher is yet to be assigned";
        return this.teacher.getName();
    }

    public List<Student> getStudentsObject() {
        return new ArrayList<>(studentsInGroup.values());
    }
    public List<String> getStudentsId(){
        return new ArrayList<>(studentsInGroup.keySet());
    }

    public int getNumberOfStudents() {
        return studentsInGroup.size();
    }

    public String getModuleCode() {
        return module.getModuleCode();
    }

    public List<Room> getRooms()
    {
        return this.rooms; 
    }

    public String getGroupId()
    {
        return this.groupId; 
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(studentsInGroup.isEmpty()){
            sb.append("No Students in course");
        }
        else {
            // Build comma-separated list of student IDs
            for (Student s : studentsInGroup.values()) {
                sb.append(s.getUserId()).append(" ").append(s.getName()).append("\n");
            }
            // deletes last \n
            if (!sb.isEmpty()) sb.deleteCharAt(sb.length() - 1);
        }

        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", module=" + module.getModuleCode() +
                ", year=" + year.getYearNumber() +
                ", presiding teacher=" + this.teacher.getTeacherId() +
                ", students=[" + sb +
                "], rooms=" + rooms +
                '}';
    }



}
