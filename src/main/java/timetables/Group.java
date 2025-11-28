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
 * Contains:
 *      - Map of studentID numbers to Student objects (studentsInGroups)
 *      - Map of roomID numbers to Room objects (rooms)
 *      - Map of dayTime codes to TimeSlot (referencedTimeSlots)
 *      - Set of all the dayTime codes that already have a TimeSlot on the timetable (usedTimes)
 *      - Set of all TimeSlots (allTimeSlots)
 *      - Map of all the dayTime codes to lecture TimeSlots (lecturesTimes)
 *      - Map of all the dayTime codes to lab TimeSlots (labsTimes)
 *      - Map of all the dayTime codes to tutorial TimeSlots (tutorialsTimes)
 *      - List of CourseFull objects that take this module (coursesTakingThisModule)
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

    /**
     * Constructor that creates the group of students taking a module
     * @param courseModule a module
     * @param coursesTakingThisModule list of courses that take this module
     * @throws IllegalArgumentException if module is null
     */
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
    }

    /**
     * Gets all the TimeSlots of the students taking a module
     * @return set of all TimeSlots
     */
    public Set<TimeSlot> getTimeSlots(){
        return allTimeSlots;
    }

    /**
     * Creates a timeslot (lecture) for this group/module and accounts for creation of
     * a lecture by adding 1 to the total lecture hours for that module.
     * @param day the day of the week
     * @param time the time of day
     * @param roomId the rooms ID number
     * @param teacherId the teachers ID number
     * @throws IllegalArgumentException if the given dayTime already has a TimeSlot
     */
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
    /**
     * Allows creation of timeslots in labs/tutorials for this group/module.
     * Accounts for creation of the selected class by adding 1 to the total number of
     * lab/tutorial hours for that module.
     * @param typeOfClass type of class (lab, tutorial)
     * @param day day of the week
     * @param time the time of day
     * @param roomId room ID number
     * @param teacherId teacher ID number
     * @throws IllegalArgumentException if the given dayTime for a lab already has a TimeSlot
     * @throws IllegalArgumentException if the given dayTime for a tutorial already has a TimeSlot
     * @throws IllegalArgumentException if lab or tutorial weren't selected
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
            throw new IllegalArgumentException("This type of class doesn't exist, \n please chose lab or tutorial");
        }
    }

    /**
     * Gets the TimeSlots for the given type of class
     * @param typeOfClass type of class
     * @return list of TimeSlots
     * @throws IllegalArgumentException if lecture, lab or tutorial weren't selected
     */
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

    /**
     * Adds a Student object to a map of students in this module group, using a given student ID
     * @param studentId student ID number
     */
    public void addStudent(String studentId){
        studentsInGroup.put(studentId, Student.getStudentFromId(studentId));
    }

    /**
     * Check if the given studentID has a corresponding Student object in this group
     * @param studentId student ID number
     * @return true if the studentId has a Student, false otherwise
     */
    public boolean containsStudent(String studentId){
        return studentsInGroup.containsKey(studentId);
    }

    /**
     * Removes a Student object from the group of students in this module
     * @param studentId student ID number
     * @throws IllegalArgumentException if the given student ID isn't in the group of students
     */
    public void removeStudent(String studentId){
        if(containsStudent(studentId))
            studentsInGroup.remove(studentId);
        else throw new IllegalArgumentException("studentId: " +studentId+" does not exist in this group");
    }

    /**
     * Sets the teacher/lecturer of this module with a given teacher ID
     * @param teacherId teacher ID number
     */
    public void setTeacher(String teacherId){
        this.lecturer = Teacher.getTeacherFromId(teacherId);
    }

    /**
     * Gets the teacher/lecturer of this module
     * @return Teacher object
     */
    public Teacher getTeacherObject() {
        return lecturer;
    }

    /**
     * Gets the name of the teacher of this module
     * @return teachers name / String indicating that this module has no teacher
     */
    public String getTeacherName(){
        if(this.lecturer == null) return "Teacher is yet to be assigned";
        return this.lecturer.getName();
    }

    /**
     * Gets list of Student objects in this group
     * @return list of Student objects
     */
    public List<Student> getStudentsObject() {
        return new ArrayList<>(studentsInGroup.values());
    }

    /**
     * Gets list of student IDs in this group
     * @return list of student IDs
     */
    public List<String> getStudentsId(){
        return new ArrayList<>(studentsInGroup.keySet());
    }

    /**
     * Gets the number of students in this group
     * @return number of students
     */
    public int getNumberOfStudents() {
        return studentsInGroup.size();
    }

    /**
     * Gets the list of rooms
     * @return list of Room objects
     */
    public List<Room> getRooms() {
        return new ArrayList<>(this.rooms.values());
    }

    /**
     * Gets the module from which this group are a part of.
     * @return the module of this group of students
     */
    public CourseModule getCourseModule(){
        return this.module;
    }

    /**
     * toString for group details
     * @return String of information about the group
     */
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
                "], rooms=" + rooms +
                '}';
    }



}
