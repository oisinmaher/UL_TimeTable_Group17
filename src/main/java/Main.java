import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import rooms.LabRoom;
import rooms.LectureRoom;
import timetables.Group;
import timetables.TimeSlot;
import users.Student;
import users.Teacher;

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
//
//        CourseFull courseFull = new CourseFull("LM121", "Computer Science");
//        courseFull.addCourseYear("1");
//        CourseYear courseYear = courseFull.getCourseYears().get(0);
//        courseYear.addSemester("spring");
//        CourseSemester semester = courseYear.getSemesters().get(0);
//        semester.addNewModule("CS4044", "OOP");
//        semester.addNewModule("Cs4011", "game dev");
////        System.out.println("module codes are " + semester.getModuleCodes());
//        CourseModule courseModule = CourseModule.getModuleFromCode(semester.getModuleCodes().get(0));
//        Group group = courseModule.getGroupAssigned();
////        SubGroup sg1 = group.createSubGroup("lab");
////        sg1.add
////        SubGroup sg2 = group.createSubGroup("tut");
////        group.addTimeSlot("lab", "mon", "1500");
////        group.addTimeSlot("tutorial", "tue", "0900");
//        CourseTimeTable.main(new String[0]);
//
//        System.out.println(courseModule.toString());
//        Scanner sc = new Scanner(System.in);
//        InMemoryTeacherService m = new InMemoryTeacherService();
//        AdminCommandSession ad = new AdminCommandSession(sc, new InMemoryStudentService(), m, new InMemoryCourseFullService(), new InMemoryCourseYearService());
////        ad.run();
//        TimeSlot timeSlot = new TimeSlot("tue", "1550", null, null);
//        System.out.println(timeSlot.getTime());
        CourseFull courseFull = new CourseFull("LM121", "Computer Science");
        CourseYear courseYear = courseFull.addCourseYear("1");

        CourseSemester courseSemester = courseYear.addSemester("autumn");
        courseSemester.addNewModule("cs4023", "Operating systems");
        courseSemester.addNewModule("cs4401", "Database");

        CourseModule dataBaseModule = courseSemester.getModuleFromCode("cs4401");
        Group dataBaseGroup = dataBaseModule.getGroupAssigned(); //group created ; added empty group made above
        CourseModule opsModule = courseSemester.getModuleFromCode("cs4023");
        Group opsGroup = opsModule.getGroupAssigned();

        LectureRoom lectureRoom = new LectureRoom("CSG001", "lecture", 100);
        LabRoom labRoom = new LabRoom("CS3005B", "lab", 100);

        Teacher teacher = new Teacher("434", "Michael English");
        Teacher teacher1 = new Teacher("432", "Caoimhe");
        Student student = new Student("24377112", "Oisin", "LM121", "1");
        Student student2 = new Student("424234", "Yousef", "LM121", "1");
        Student student3 = new Student("4322545", "Alex", "LM121", "1");
        dataBaseGroup.addClassTimes("lab", "mon", "1500", labRoom.getRoomID(), teacher.getUserId()); //adding times
        opsGroup.addClassTimes("tut", "tues", "1205", labRoom.getRoomID(), teacher1.getUserId());
        opsGroup.addLectureTime("mon", "1200", lectureRoom.getRoomID(), teacher.getUserId());

        TimeSlot dbSlot = dataBaseGroup.getTimeSlots("lab").get(0);
        TimeSlot opsSlot = opsGroup.getTimeSlots("tut").get(0);
        opsSlot.addStudent("24377112");
        opsSlot.addStudent("424234");
        dbSlot.addStudent("24377112");

        //student.getUserTimeTable().printTimeTable();
            courseFull.getCourseTimeTable().printTimeTable();
    }
}
