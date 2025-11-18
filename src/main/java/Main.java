package src.main.java;
import src.main.java.modules.CourseModule;
import src.main.java.programCourse.CourseFull;
import src.main.java.programCourse.CourseSemester;
import src.main.java.programCourse.CourseYear;
//import src.main.java.modules.CourseModule;
//import src.main.java.rooms.LectureRoom;
//import src.main.java.rooms.Room;
//import src.main.java.ui.cli1.AdminCommandSession;
//import src.main.java.timetables.*;
//import src.main.java.ui.cli1.InMemoryLecturerService;
//import src.main.java.ui.cli1.InMemoryStudentService;
//import src.main.java.ui.cli1.StudentService;
//import src.main.java.users.Student;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        CourseModule oop = new CourseModule("OOP", "CS4013");
//        CourseModule swTest = new CourseModule("SW Testing", "CS4004");
//        CourseModule os = new CourseModule("Operating Systems", "CS4023");
//        CourseModule swReq = new CourseModule("SW Requirements", "CS4178");
//        oop.setLecHours(6);
//        LectureRoom csg001 = new LectureRoom("CSG-001", " ", 100);
//        LectureRoom d1050 = new LectureRoom("D-1050", "", 150);
//
//        d1050.setRoomType("Lecture rooms.room");
//        csg001.setRoomType("Lecture rooms.Room");
//
//        Set<Room> lm051Rooms = new HashSet<Room>();
//        lm051Rooms.add(d1050);
//        lm051Rooms.add(csg001);
//
//        //Test code (uses Strings instead of modules and room object)
//        Student student = new Student("Oisin", 24377112, 121);
//        student.addClassToTimeTable("OOP", "Lecture", "CSG001", "Tuesday 1PM");
//        student.displayTimeTable();
//
//        List<Student> group1a = Arrays.asList(student);
//
//
//
//
//
//        List<CourseModule> year1Sem1Modules = Arrays.asList(oop, swTest);
//        List<CourseModule> year1Sem2Modules = Arrays.asList(os);
//        List<CourseModule> year2Sem1Modules = Arrays.asList(os, swReq);
//
//        ProgramWithCourseModule.ProgramSemester semester1 = new ProgramWithCourseModule.ProgramSemester("Autumn", year1Sem1Modules);
//        ProgramWithCourseModule.ProgramSemester semester2 = new ProgramWithCourseModule.ProgramSemester("Spring", year1Sem2Modules  );
//        ProgramWithCourseModule.ProgramSemester semester3 = new ProgramWithCourseModule.ProgramSemester("Autumn", year2Sem1Modules);
//
//
//        List<ProgramWithCourseModule.ProgramSemester> year1Semesters = Arrays.asList(semester1, semester2);
//        List<ProgramWithCourseModule.ProgramSemester> year2Semesters = Arrays.asList(semester3);
//        ProgramWithCourseModule.ProgramYear year1 = new ProgramWithCourseModule.ProgramYear(null, 1, year1Semesters);
//        ProgramWithCourseModule.ProgramYear year2 = new ProgramWithCourseModule.ProgramYear(null, 2, year2Semesters);
//
//        List<ProgramWithCourseModule.ProgramYear> allYears = Arrays.asList(year1, year2);
//        ProgramWithCourseModule compSciProgram = new ProgramWithCourseModule("BSc", "Computer Systems", allYears);
//
//        System.out.println("Year1 modules: " + year1.allModules());
//        System.out.println("Year2 Modules: " + year2.allModules() );
//        System.out.println("Autumn semester: " + year1.modulesForSemester("Autumn"));
//        System.out.println("Spring Semester: " + year1.modulesForSemester("Spring"));
//        System.out.println("Autumn Semester: " + year2.modulesForSemester("Autumn"));
//
//        System.out.println(semester1);
//        System.out.println(semester2);
//
//        ProgramSemester lm0512526Sem1 = new ProgramSemester("lm051AY25Sem1", year1Sem1Modules);
//        ProgramSemester lm0512526Sem2 = new ProgramSemester("lm051AY25Sem1", year1Sem2Modules);
//
//
//        ProgramYear AY2025Y1 = new ProgramYear(null, 1, Arrays.asList(lm0512526Sem1, lm0512526Sem2));
//
//
//        Scanner sc = new Scanner(System.in);
//        AdminCommandSession ad = new AdminCommandSession(sc, new InMemoryStudentService(), new InMemoryLecturerService());
//        ad.run();
        CourseFull courseFull = new CourseFull("LM121", "Computer Science");
        courseFull.addCourseYear("1");
        CourseYear courseYear = courseFull.getCourseYears().getFirst();
        courseYear.addSemester("spring");
        CourseSemester semester = courseYear.getSemesters().getFirst();
        semester.addNewModule("CS4044", "OOP");
        semester.addNewModule("Cs4011", "game dev");
        System.out.println("module codes are " + semester.getModuleCodes());
        CourseModule courseModule = CourseModule.getModuleFromCode(semester.getModuleCodes().getFirst());
        System.out.println(courseModule.toString());


    }
}
