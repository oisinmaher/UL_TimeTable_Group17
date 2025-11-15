package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.LectureRoom;
import src.main.java.users.Student;
import src.main.java.users.Teacher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A TimeSlot is the place in the timetable that keeps the information of each user, room and module at a specific place
 * in the timetable
 * Its main "key" is the time, this is used in its comparison function (so TreeSets can be ordered)
 * It holds info about time, room number, program (course associated), teacher, and listOfStudents
 */
public class TimeSlot implements Comparable<TimeSlot> {
    private String time;
    private Room lectureRoom;
    private Teacher teacher;
    //these could be replaced by a program object
    private CourseModule module;
    private List<Student> students;

    /**
     *
     * @param time the time of day that the timeslot starts in 24-hour clock (9 am is 0900)
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
    public TimeSlot(String time, LectureRoom lectureRoom){
        this.time = time;
        this.lectureRoom = lectureRoom;
        students = new ArrayList<>();
    }
    // Add a single student
    public void addStudent(Student student){
        students.add(student);
    }
    // Remove a single student
    public void removeStudent(Student student){
        students.remove(student);
    }
    // add list of students
    public void addStudents(List<Student> students){
        this.students.addAll(students);
    }
    // remove list of students
    public void removeStudents(List<Student> students){
        this.students.removeAll(students);
    }
    // assign teacher
    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }
    public void setRoom(Room lectureRoom){
        this.room = lectureRoom;
    }



    /**
     * Compares a TimeSlot with another TimeSlot for order based on time
     * @param o the object to be compared.
     * @return a negative integer, zero or a positive integer if the TimeSlot is earlier, the same or after the other
     */
    @Override
    public int compareTo(TimeSlot o) {
        // Compares the String time (that's associated with everytime slot)
        // With each other to maintain order. Its lexographically compared
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
