package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.LectureRoom;
import src.main.java.users.Student;

import java.util.Collection;
import java.util.List;

/**
 * A TimeSlot is the place in the timetable that keeps the information of each user, room and module at a specific place
 * in the timetable
 */
public class TimeSlot implements Comparable<TimeSlot> {
    private String time;
    private LectureRoom lectureRoom;

    //these could be replaced by a program object
    private CourseModule module;
    private List<Student> students;

    /**
     *
     * @param time the time of day that the timeslot starts
     * @param module the module that occurs at the time
     * @param students the student that attends the timeslot
     * @param lectureRoom the lecture room that the timeslot occurs at
     */
    public TimeSlot(String time, CourseModule module, List<Student> students, LectureRoom lectureRoom){
        this.time = time;
        this.module = module;
        this.students = students;
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
        StringBuilder sb = new StringBuilder();
        for(Student s:students ){
            sb.append(s.getUserId()).append(", ");
        }

        return "Time:" + time + ", Module: " + module.getModuleName() + ", Student: " + sb + ", Room: " + lectureRoom.getRoomID();
    }

}
