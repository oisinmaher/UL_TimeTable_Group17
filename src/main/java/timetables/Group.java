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
 *
 */
public class Group {

    protected final CourseModule module;
    protected final String moduleCode;
    protected final Map<String, Student> studentsInGroup;
    protected Teacher lecturer;
    private final Map<String, Room> rooms;
    private final Map<String, TimeSlot> referencedTimeSlots;
    protected Set<String> usedTimes;
    protected Set<TimeSlot> allTimeSlots;
    protected Map<String, TimeSlot> lecturesTimes;
    protected Map<String, TimeSlot> labsTimes;
    protected Map<String, TimeSlot> tutorialsTimes;
    protected List<CourseFull> coursesTakingThisModule;
    private final static Map<String, Group> allGroups = new HashMap<>();

    public Group(CourseModule courseModule, List<CourseFull> coursesTakingThisModule){
        if (courseModule == null) {
            throw new IllegalArgumentException("Module can't be null");
        }
        this.module = courseModule;
        this.moduleCode = courseModule.getModuleCode().toLowerCase();
        this.allTimeSlots = new HashSet<>();
        this.coursesTakingThisModule = coursesTakingThisModule;
        this.lecturer = null;
        rooms = new HashMap<>();
        referencedTimeSlots = new HashMap<>();
        studentsInGroup = new HashMap<>();
        this.lecturesTimes = new HashMap<>();
        this.labsTimes = new HashMap<>();
        this.tutorialsTimes = new HashMap<>();
        this.usedTimes = new HashSet<>();
        allGroups.put(moduleCode, this);
    }
    public Set<TimeSlot> getTimeSlots(){
        return allTimeSlots;
    }
    public void addLectureTime(String day, String time, String roomId, String teacherId){
        TimeSlot timeSlot = new TimeSlot(day, time, module, coursesTakingThisModule, this, "Lecture", roomId, teacherId);
        if(usedTimes.contains(timeSlot.getTime())){
           throw new IllegalArgumentException("This timeslot is already taken");
        }
        int prevLecHours = module.getLecHours();
        module.setLecHours(prevLecHours + 1);
        usedTimes.add(timeSlot.getTime());
        allTimeSlots.add(timeSlot);
        lecturesTimes.put(timeSlot.getTime(), timeSlot);
        referencedTimeSlots.put(timeSlot.getTime(), timeSlot);
    }

    public static Map<String, Group> getAllGroups(){
        return allGroups;
    }
    /**
     * Allows creation of timeslots in labs/tutorials for this group/module
     *
     * @param typeOfClass
     * @param day
     * @param time
     */
    public void addClassTimes(String typeOfClass, String day, String time, String roomId, String teacherId){
        typeOfClass = typeOfClass.toLowerCase();
        if(typeOfClass.equals("lab")){
            TimeSlot timeSlot = new TimeSlot(day, time, module, coursesTakingThisModule, this, "Lab", roomId, teacherId);
            if(usedTimes.contains(timeSlot.getTime())){
                throw new IllegalArgumentException("This timeslot is already used");
            }
            int prevLabHours = module.getLabHours();
            module.setLabHours(prevLabHours+1);
            usedTimes.add(timeSlot.getTime());
            allTimeSlots.add(timeSlot);
            labsTimes.put(timeSlot.getTime(), timeSlot);
            referencedTimeSlots.put(timeSlot.getTime(), timeSlot);
        }
        else if(typeOfClass.equals("tutorial") || typeOfClass.equals("tut")){
            TimeSlot timeSlot = new TimeSlot(day, time, module, coursesTakingThisModule, this, "Tutorial", roomId, teacherId);
            if(usedTimes.contains(timeSlot.getTime())){
                throw new IllegalArgumentException("This timeslot is already used");
            }
            int prevTutHours = module.getTutHours();
            module.setTutHours(prevTutHours + 1);
            usedTimes.add(timeSlot.getTime());
            allTimeSlots.add(timeSlot);
            tutorialsTimes.put(timeSlot.getTime(), timeSlot);
            referencedTimeSlots.put(timeSlot.getTime(), timeSlot);
        }
        else{
            throw new IllegalArgumentException("This type of class doesnt exist, \n please chose lab or tutorial");
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
        else if(typeOfClass.equals("tutorial") || typeOfClass.equals("tut")){
            return new ArrayList<>(tutorialsTimes.values());
        }
        else{
            throw new IllegalArgumentException(typeOfClass + " isn't a type of class \nPlease choose lecture, lab, or tutorial");
        }
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
        this.lecturer = Teacher.getTeacherFromId(teacherId);
    }
    public Teacher getTeacherObject() {
        return lecturer;
    }
    public String getTeacherName(){
        if(this.lecturer == null) return "Teacher is yet to be assigned";
        return this.lecturer.getName();
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


    public List<Room> getRooms()
    {
        return new ArrayList<>(this.rooms.values());
    }

    public List<CourseFull> getCoursesTakingThisModule(){
        return this.coursesTakingThisModule;
    }


    public CourseModule getCourseModule(){
        return this.module;
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
                ", module=" + moduleCode +
                ", presiding teacher=" + this.lecturer.getUserId() +
                ", students=[" + sb +
                "], rooms=" + getRooms() +
                '}';
    }



}
