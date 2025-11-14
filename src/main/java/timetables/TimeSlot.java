package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.LectureRoom;
import src.main.java.users.Student;

/**
 *
 */
public class TimeSlot implements Comparable<TimeSlot> {
    private String time;
    private LectureRoom lectureRoom;

    //these could be replaced by a program object
    private CourseModule module;
    private Student student;

    /**
     *
     * @param time
     * @param module
     * @param student
     * @param lectureRoom
     */
    public TimeSlot(String time, CourseModule module, Student student, LectureRoom lectureRoom){
        this.time = time;
        this.module = module;
        this.student = student;
        this.lectureRoom = lectureRoom;
    }

    /**
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(TimeSlot o) {
        return this.time.compareTo(o.time);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "Time:" + time + ", Module: " + module.getModuleName() + ", Student: " + student.getUserId() + ", Room: " + lectureRoom.getRoomID();
    }
}
