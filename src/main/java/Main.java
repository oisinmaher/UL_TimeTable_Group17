package src.main.java;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.LectureRoom;
import src.main.java.timetables.TimeSlot;
import src.main.java.timetables.TimeTable;
import src.main.java.users.Student;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       CourseModule oop = new CourseModule("OOP", "CS4013");
        oop.setLecHours(6);

        System.out.println("Lecture hours=" + oop.getLecHours() + ", module=" + oop.getModuleCode() +
                ", name=" + oop.getModuleName());

        LectureRoom csg001 = new LectureRoom("CSG-001", " ", 100);

        csg001.setRoomType("Lecture rooms.Room");
        System.out.println("Roomtype=" + csg001.getRoomType() + ", name=" + csg001.getRoomID() +
               ", capacity=" + csg001.getMaxCapacity());


        //Test code (uses Strings instead of modules and room object)
        Student student = new Student("Oisin", 24377112, 121);
        student.addClassToTimeTable("OOP", "Lecture", "CSG001", "Tuesday 1PM");
        student.displayTimeTable();

        TimeTable timeTable = new TimeTable();
        TimeSlot monday9am = new TimeSlot("0900", oop, student, csg001);

        timeTable.addTimeSlot("Monday", monday9am);

        System.out.println(timeTable.toString());

    }
}
