package timetables;
import modules.CourseModule;
import programCourse.CourseFull;
import rooms.Room;
import users.Student;
import users.Teacher;
import users.User;

import java.util.*;

/**
 * TimeSlot class.
 * A TimeSlot is the place in the timetable that keeps the information of each user, room and module at a specific place
 * in the timetable.
 * Contains:
 *      - Map of studentID to Student object (students)
 */
public class TimeSlot{
    // day is mon-fri represented as 1-5, time is 24 hour formatted 9am to 5pm as 09 -> 17
    private String dayTime;
    private Teacher teacher;
    private Map<String, Student> students;
    private Room room;
    private String classType;
    private CourseModule module;
    List<CourseFull> coursesTakingThisModule;
    private Group group;
    static List<String> daysOfWeek = new ArrayList<>(Arrays.asList("mon","tue","wed","thu","fri"));
    static Set<String> validTimes = new HashSet<>(Arrays.asList("08","09","10","11","12","13","14","15","16","17"));

    /**
     * Constructor that adds this TimeTable to the teachers timetable and to the timetables
     * of the courses involved
     * @param day the day of the week
     * @param time the time of day
     * @param module a CourseModule object
     * @param coursesTakingThisModule a list of
     * @param classType type of class
     * @param roomId room ID number
     * @param teacherId teachers ID number
     */
    public TimeSlot(String day, String time, CourseModule module, List<CourseFull> coursesTakingThisModule, String classType, String roomId, String teacherId){
        this.dayTime = toCorrectTimeFormat(day, time);
        this.module = module;
        this.coursesTakingThisModule = coursesTakingThisModule;
        this.group = group;
        this.module = group.getCourseModule();
        this.teacher = Teacher.getTeacherFromId(teacherId);
        this.students = new HashMap<>();
        UserTimeTable teacherTimeTable = teacher.getUserTimeTable();
        teacherTimeTable.addTimeSlot(dayTime, this);
        this.room = Room.getRoomFromId(roomId);
        for(CourseFull courseFull : coursesTakingThisModule)
            courseFull.getCourseTimeTable().addTimeSlot(dayTime, this);
        this.classType = classType;

    }

    /**
     * Adds a student to a TimeSlot, and adds this TimeSlot to the students timetable
     * @param studentId students ID number
     * @throws IllegalArgumentException if the student is already a part of this TimeSlot
     */
    public void addStudent(String studentId){
        Student student = Student.getStudentFromId(studentId);
        if(students.containsKey(studentId)){
            throw new IllegalArgumentException("Student with id " + studentId + " already exists");
        }
        students.put(studentId, student);
        UserTimeTable userTimeTable = student.getUserTimeTable();
        userTimeTable.addTimeSlot(this.dayTime, this);
    }

    /**
     * Add each students ID number to a TimeSlot
     * @param studentIds list of students ID numbers
     */
    public void addStudents(List<String> studentIds){
        for(String studentId : studentIds){
            addStudent(studentId);
        }
    }

    /**
     * Checks if this TimeSlot has a Student object, using a given student ID
     * @param studentId student ID number
     * @return true if the student ID found a Student, false otherwise
     */
    public boolean containsStudent(String studentId){
        return students.containsKey(studentId);
    }

    /**
     * Sets the Room that this TimeSlot will be held in
     * @param room Room object
     */
    public void setRoom(Room room){
        this.room = room;
    }

    /**
     * Gets the module of this timeslot
     * @return CourseModule
     */
    public CourseModule getModule() {
        return module;
    }

    /**
     * Gets all the student IDs to Student
     * @return all students in this timeslot
     */
    public Map<String, Student> getStudents() {
        return students;
    }

    /**
     * Gets the courses taking this module
     * @return courses involved in this timeslot
     */
    public List<CourseFull> getCoursesTakingThisModule() {
        return coursesTakingThisModule;
    }

    /**
     * Gets the room where this timeslot is held
     * @return Room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Gets the teacher of this timeslot
     * @return Teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Gets the type of class of this timeslot
     * @return class type
     */
    public String getClassType() {
        return classType;
    }

    /**
     * This converts monday 1600 to 1_16, where 1 represents day (monday is 1st day of week) and 16 (represents 16:00)
     * This is useful because we can put all times in a single TreeSet that will be able to easily sort based
     * on the strings lexicographical size
     * @param day the day of the week
     * @param time the time of day
     * @return the day and time in the correct format
     * @throws IllegalArgumentException if the given day is not between mon - fri
     * @throws IllegalArgumentException if the given time is not between 09:00 - 17:00
     */
    public static String toCorrectTimeFormat(String day, String time){
        day = day.toLowerCase();
        // NEED CHECK FOR IF DAY IS mon tue wed thu fri
        if(day.length() > 3){
            day = day.substring(0,3);
        }
        // +1, because for example monday would be 0 (doesn't really matter regardless)
        day = Integer.toString(daysOfWeek.indexOf(day) + 1);
        if(day.equals("-1")){
            throw new IllegalArgumentException("This isn't a valid day, it has to be: " + daysOfWeek);
        }
        if(time.length() > 2){
            time = time.substring(0, 2);
        }
        if(time.length() == 1){
            time = "0" + time;
        }
        if(!validTimes.contains(time)){
            throw new IllegalArgumentException("Invalid time");
        }
        return day + "_" + time;
    }

    /**
     * Gets the dayTime code of this TimeSlot
     * @return dayTime code
     */
    public String getTime(){
        return this.dayTime;
    }

    /**
     * Gets the day of the week from a given number
     * @param num number that represents a day of the week
     * @return the day of the week
     */
    public static String getDayFromNumber(int num){
        return switch (num) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            default -> "null";
        };
    }

    /**
     * Gets the time of day from a given hour time
     * @param time number representing the hour only
     * @return the full time
     */
    public static String getTimeFromShortened(int time){
        return switch (time){
            case 9 -> "9:00";
            case 10 -> "10:00";
            case 11 -> "11:00";
            case 12 -> "12:00";
            case 13 -> "13:00";
            case 14 -> "14:00";
            case 15 -> "15:00";
            case 16 -> "16:00";
            case 17 -> "17:00";
            default -> "null";
        };
    }


    /**
     * Returns the timeslot object as a string
     * @return the time, module, student and room
     */
    @Override
    public String toString(){
        return "Module Code: " + module.toString() + " Class type: " + classType + " Room: " + room.toString() + " Teacher Name: " + teacher.toString();
    }

}
