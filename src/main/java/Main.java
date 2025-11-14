package src.main.java;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.LectureRoom;
import src.main.java.timetables.ProgramWithCourseModule;
import src.main.java.timetables.TimeSlot;
import src.main.java.timetables.TimeTable;
import src.main.java.users.Student;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CourseModule oop = new CourseModule("OOP", "CS4013");
        CourseModule swTest = new CourseModule("SW Testing", "CS4004");
        CourseModule os = new CourseModule("Operating Systems", "CS4023");
        CourseModule swReq = new CourseModule("SW Requirements", "CS4178");
        oop.setLecHours(6);
        LectureRoom csg001 = new LectureRoom("CSG-001", " ", 100);
        csg001.setRoomType("Lecture rooms.Room");

        //Test code (uses Strings instead of modules and room object)
        Student student = new Student("Oisin", 24377112, 121);
        student.addClassToTimeTable("OOP", "Lecture", "CSG001", "Tuesday 1PM");
        student.displayTimeTable();

        List<Student> group1a = Arrays.asList(student);

        TimeTable timeTable = new TimeTable();
        TimeSlot monday9am = new TimeSlot("0900", oop, group1a, csg001);

        timeTable.addTimeSlot("Monday", monday9am);
        timeTable.addTimeSlot("Tuesday", new TimeSlot("1000", swReq, group1a, csg001));

        System.out.println(timeTable);

        List<CourseModule> year1Sem1Modules = Arrays.asList(oop, swTest);
        List<CourseModule> year1Sem2Modules = Arrays.asList(os);
        List<CourseModule> year2Sem1Modules = Arrays.asList(os, swReq);

        ProgramWithCourseModule.ProgramSemester semester1 = new ProgramWithCourseModule.ProgramSemester("Autumn", year1Sem1Modules);
        ProgramWithCourseModule.ProgramSemester semester2 = new ProgramWithCourseModule.ProgramSemester("Spring", year1Sem2Modules  );
        ProgramWithCourseModule.ProgramSemester semester3 = new ProgramWithCourseModule.ProgramSemester("Autumn", year2Sem1Modules);


        List<ProgramWithCourseModule.ProgramSemester> year1Semesters = Arrays.asList(semester1, semester2);
        List<ProgramWithCourseModule.ProgramSemester> year2Semesters = Arrays.asList(semester3);
        ProgramWithCourseModule.ProgramYear year1 = new ProgramWithCourseModule.ProgramYear(null, 1, year1Semesters);
        ProgramWithCourseModule.ProgramYear year2 = new ProgramWithCourseModule.ProgramYear(null, 2, year2Semesters);

        List<ProgramWithCourseModule.ProgramYear> allYears = Arrays.asList(year1, year2);
        ProgramWithCourseModule compSciProgram = new ProgramWithCourseModule("BSc", "Computer Systems", allYears);

        System.out.println("Year1 modules: " + year1.allModules());
        System.out.println("Year2 Modules: " + year2.allModules() );
        System.out.println("Autumn semester: " + year1.modulesForSemester("Autumn"));
        System.out.println("Spring Semester: " + year1.modulesForSemester("Spring"));
        System.out.println("Autumn Semester: " + year2.modulesForSemester("Autumn"));

        System.out.println(semester1);
        System.out.println(semester2);





    }
}
