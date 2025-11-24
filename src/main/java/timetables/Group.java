package timetables;
import modules.CourseModule;
import programCourse.*;
import rooms.Room;
import users.Student;
import users.Teacher;

import java.sql.Time;
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
    private final Set<String> usedTimes;
    private Map<String, TimeSlot> lecturesTimes;
    private Map<String, TimeSlot> labsTimes;
    private Map<String, TimeSlot> tutorialsTimes;

    public Group(String moduleCode){
        if (moduleCode == null) {
            throw new IllegalArgumentException("Module can't be null");
        }
        moduleCode = moduleCode.toLowerCase();
        this.moduleCode = moduleCode;
        this.module = CourseModule.getModuleFromCode(moduleCode);
        this.teacher = null;
        rooms = new ArrayList<>();
        referencedSubGroups = new HashMap<>();
        studentsInGroup = new HashMap<>();
        this.lecturesTimes = new HashMap<>();
        this.labsTimes = new HashMap<>();
        this.tutorialsTimes = new HashMap<>();
        this.usedTimes = new HashSet<>();
    }

    /**
     * Allows creation of timeslots for this group/module
     *
     * @param typeOfClass
     * @param day
     * @param time
     */
    public void addTimeSlot(String typeOfClass, String day, String time){
        typeOfClass = typeOfClass.toLowerCase();
        if(typeOfClass.equals("lecture")){
            TimeSlot timeSlot = new TimeSlot(day, time, module);
            if(usedTimes.contains(timeSlot.toString())){
                throw new IllegalArgumentException("This timeslot is already used");
            }
            usedTimes.add(timeSlot.toString());
            lecturesTimes.put(timeSlot.toString(), timeSlot);
        }
        else if(typeOfClass.equals("lab")){
            TimeSlot timeSlot = new TimeSlot(day, time, module);
            if(usedTimes.contains(timeSlot.toString())){
                throw new IllegalArgumentException("This timeslot is already used");
            }
            usedTimes.add(timeSlot.toString());
            labsTimes.put(timeSlot.toString(), timeSlot);
        }
        else if(typeOfClass.equals("tutorial")){
            TimeSlot timeSlot = new TimeSlot(day, time, module);
            if(usedTimes.contains(timeSlot.toString())){
                throw new IllegalArgumentException("This timeslot is already used");
            }
            usedTimes.add(timeSlot.toString());
            tutorialsTimes.put(timeSlot.toString(), timeSlot);
        }
        else{
            throw new IllegalArgumentException("This type of class doesnt exist, \n please chose lecture, lah or tutorial");
        }
    }
    public List<TimeSlot> getTimeSlots(String typeOfClass){
        typeOfClass = typeOfClass.toLowerCase();
        if(typeOfClass.equals("lecture")){
            return new ArrayList<>(lecturesTimes.values());
        }
        else if(typeOfClass.equals("lab")){
            return new ArrayList<>(labsTimes.values());
        }
        else if(typeOfClass.equals("tutorial")){
            return new ArrayList<>(tutorialsTimes.values());
        }
        else{
            throw new IllegalArgumentException(typeOfClass + " isn't a type of class \nPlease choose lecture, lab, or tutorial");
        }
    }
    public void createSubGroup(String typeOfClass){
        typeOfClass = typeOfClass.toLowerCase();
        SubGroup subGroup = new SubGroup(moduleCode, typeOfClass);
        referencedSubGroups.put(subGroup.getSubGroupId(), subGroup);
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
