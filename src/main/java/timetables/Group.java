package timetables;
import modules.CourseModule;
import rooms.Room;
import users.Student;
import users.Teacher;

import java.util.*;

/**
 * Group will be the students in a given module at a specific semester
 * This and module could be merged into one group, and subgroup will be its own class without inheritance
 */
public class Group {

    protected final CourseModule module;
    protected final String moduleCode;
    protected final Map<String, Student> studentsInGroup;
    private Teacher teacher;
    private final List<Room> rooms;
    private final Map<String, SubGroup> referencedSubGroups;

    public Group(String moduleCode){
        if (moduleCode == null) {
            throw new IllegalArgumentException("Module can't be null");
        }
        this.moduleCode = moduleCode;
        this.module = CourseModule.getModuleFromCode(moduleCode);
        this.teacher = null;
        rooms = new ArrayList<>();
        referencedSubGroups = new HashMap<>();
        studentsInGroup = new HashMap<>();
    }
    public void createSubGroup(){

    }
    public void addStudent(String studentId){
        studentsInGroup.put(studentId, Student.getStudentFromId(studentId));
    }
    public boolean containsStudent(String studentId){
        return studentsInGroup.containsKey(studentId);
    }
    public void removeStudent(String studentId){
        if(containsStudent(studentId))
            studentsInGroup.remove(studentId);
        else throw new IllegalArgumentException("studentId: " +studentId+" does not exist in this group");
    }


    public void setTeacher(String teacherId){
        this.teacher = Teacher.getTeacherFromId(teacherId);
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

    public String getGroupModuleCode()
    {
        return this.module.getModuleCode();
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
                ", module=" + module.getModuleCode() +
                ", presiding teacher=" + this.teacher.getTeacherId() +
                ", students=[" + sb +
                "], rooms=" + rooms +
                '}';
    }



}
