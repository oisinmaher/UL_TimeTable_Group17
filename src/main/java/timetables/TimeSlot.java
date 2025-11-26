package timetables;
import modules.CourseModule;
import programCourse.CourseFull;
import rooms.Room;
import users.Student;
import users.Teacher;
import users.User;

import java.util.*;

/**
 * A TimeSlot is the place in the timetable that keeps the information of each user, room and module at a specific place
 * in the timetable
 */
public class TimeSlot{
    // day is mon-fri represented as 1-5, time is 24 hour formatted 9am to 5pm as 09 -> 17
    private String dayTime;
    private Teacher teacher;
    private Map<String, Student> students;
    private Room room;
    private String classType;
    private CourseModule module;
    private CourseFull courseFull;
    private Group group;
    static List<String> daysOfWeek = new ArrayList<>(Arrays.asList("mon","tue","wed","thu","fri"));
    static Set<String> validTimes = new HashSet<>(Arrays.asList("08","09","10","11","12","13","14","15","16","17"));

    public TimeSlot(String day, String time, CourseModule module, CourseFull courseFull, Group group, String classType, String roomId, String teacherId){
        this.dayTime = toCorrectTimeFormat(day, time);
        this.module = module;
        this.courseFull = courseFull;
        this.group = group;
        this.module = group.getCourseModule();
        this.teacher = Teacher.getTeacherFromId(teacherId);
        this.students = new HashMap<>();
        UserTimeTable teacherTimeTable = teacher.getUserTimeTable();
        teacherTimeTable.addTimeSlot(dayTime, this);
        this.room = Room.getRoomFromId(roomId);
        courseFull.getCourseTimeTable().addTimeSlot(dayTime, this);
        this.classType = classType;
    }

//    public void changeTeacher(String teacherId){
//        Teacher oldTeacher = Teacher.getTeacherFromId(teacherId);
//        Teacher newTeacher = Teacher.getTeacherFromId(teacherId);
//        oldTeacher.getTimeTable().removeTime(this.dayTime);
//    }
    public void addStudent(String studentId){
        Student student = Student.getStudentFromId(studentId);
        if(students.containsKey(studentId)){
            throw new IllegalArgumentException("Student with id " + studentId + " already exists");
        }
        students.put(studentId, student);
        UserTimeTable userTimeTable = student.getUserTimeTable();
        userTimeTable.addTimeSlot(this.dayTime, this);
    }
    public void addStudents(List<String> studentIds){
        for(String studentId : studentIds){
            addStudent(studentId);
        }
    }
    public boolean containsStudent(String studentId){
        return students.containsKey(studentId);
    }
    public void setRoom(Room room){
        this.room = room;
    }

    /**
     * This converts monday 1600 to 1_16, where 1 represents day (monday is 1st day of week) and 16 (represents 16:00)
     * This is useful because we can put all times in a single treeset that will be able to easily sort based
     * on the strings lexographical size
     * @param day
     * @param time
     * @return
     */
    public String toCorrectTimeFormat(String day, String time){
        day = day.toLowerCase();
        // NEED CHECK FOR IF DAY IS mon tue wed thu fri
        if(day.length() > 3){
            day = day.substring(0,3);
        }
        // +1, because for example monday would be 0 (doesnt really matter regardless)
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
    public String getTime(){
        return this.dayTime;
    }
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
