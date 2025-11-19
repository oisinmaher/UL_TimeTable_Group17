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
    private final String groupId;

    // This will map it so if a module has multiple groups you can find which letter
    // to assign for its next groupId,
    // (e.g when creating a new group for a module that contains CS4004A, it will return A then you can do 'A'++
    // to get 'B', assign the group the name cs4004B and update map to B
    private static Map<String, Character> moduleRecentGroupId = new HashMap<>();

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
        // gets module name
        String moduleCode = module.getModuleCode().toLowerCase();
        studentsInGroup = new HashMap<>();
        rooms = new ArrayList<>();
        // group indicator (a letter a->z)
        char groupIndicator;
        // check if this module already has a group
        if(moduleRecentGroupId.containsKey(moduleCode)){
            // if it does the map value will be last indicator used (e.g 'a')
            groupIndicator = moduleRecentGroupId.get(moduleCode);
            // increases group indicator (example 'a' to 'b')
            groupIndicator++;
        }
        // else it sets it to first value (which will be 'a')
        else groupIndicator = 'a';
        // makes group id the moduleCode + groupIndicator (e.g. cs4004a)
        this.groupId = moduleCode + groupIndicator;
        // adds / replaces module indicatorCode in the map
        moduleRecentGroupId.put(moduleCode, groupIndicator);
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

        // Build comma-separated list of student IDs
        for (Student s: studentsInGroup.values()) {
            sb.append(s.getUserId()).append(" ").append(s.getName()).append("\n");
        }
        // deletes last \n
        if(!sb.isEmpty()) sb.deleteCharAt(sb.length()-1);

        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", module=" + module.getModuleCode() +
                ", year=" + year.getYearNumber() +
                ", presiding teacher=" + this.teacher()
                ", students=[" + sb +
                "], rooms=" + rooms +
                '}';
    }



}
