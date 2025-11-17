package timetables;

import rooms.LectureRoom;
import modules.CourseModule;
import rooms.Room;
import users.Lecturer;

import java.time.LocalTime;

public class TimeSlot implements Comparable<TimeSlot> {
    private LocalTime startTime;
    private LocalTime endTime;
    private CourseModule module;
    private Group group;
    private Room room;
    private Lecturer lecturer;

    public TimeSlot(LocalTime startTime, LocalTime endTime, CourseModule module,
                    Group group, LectureRoom room, Lecturer lecturer) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.module = module;
        this.group = group;
        this.room = room;
        this.lecturer = lecturer;
    }

    @Override
    public int compareTo(TimeSlot o) {
        return this.startTime.compareTo(o.startTime);
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime + " " + module.getModuleCode() + " Group: " + group.getYear() +
                " Room: " + room.getRoomID() +
                " Lecturer: " + lecturer.getName();
    }
}
