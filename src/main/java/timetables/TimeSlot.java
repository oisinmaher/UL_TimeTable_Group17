package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.LectureRoom;
import src.main.java.users.Student;

/**
 * A TimeSlot is the place in the timetable that keeps the information of each user, room and module at a specific place
 * in the timetable
 */
public class TimeSlot implements Comparable<TimeSlot> {
    private String time;
    private LectureRoom lectureRoom;

    //these could be replaced by a program object
    private CourseModule module;
    private Student student;

    /**
     *
     * @param time the time of day that the timeslot starts
     * @param module the module that occurs at the time
     * @param student the student that attends the timeslot
     * @param lectureRoom the lecture room that the timeslot occurs at
     */
    public TimeSlot(String time, CourseModule module, Student student, LectureRoom lectureRoom){
        this.time = time;
        this.module = module;
        this.student = student;
        this.lectureRoom = lectureRoom;
    }

    /**
     * Compares a TimeSlot with another TimeSlot for order based on time
     * @param o the object to be compared.
     * @return a negative integer, zero or a positive integer if the TimeSlot is earlier, the same or after the other
     */
    @Override
    public int compareTo(TimeSlot o) {
        return this.time.compareTo(o.time);
    }

    /**
     * Returns the timeslot object as a string
     * @return the time, module, student and room
     */
    @Override
    public String toString(){
        return "Time:" + time + ", Module: " + module.getModuleName() + ", Student: " + student.getUserId() + ", Room: " + lectureRoom.getRoomID();
    }
}
