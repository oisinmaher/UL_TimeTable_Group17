

import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import rooms.LectureRoom;
import timetables.Group;
import timetables.TimeSlot;
import ui.cli1.AdminCommandSession;
import ui.cli1.InMemoryLecturerService;
import ui.cli1.InMemoryStudentService;
import users.Lecturer;
import users.Student;

import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        // --- 1. Create Modules ---
        CourseModule cs101 = new CourseModule("Introduction to CS", "CS101");
        CourseModule ma101 = new CourseModule("Calculus I", "MA101");
        CourseModule ph101 = new CourseModule("Physics I", "PH101");

        // --- 2. Create Semesters ---
        CourseSemester autumn = new CourseSemester("Autumn", Arrays.asList(cs101, ma101));
        CourseSemester spring = new CourseSemester("Spring", Arrays.asList(ph101));

        // --- 3. Create Course Year ---
        CourseYear year1 = new CourseYear(null, 1, Arrays.asList(autumn, spring));

        // --- 4. Create Course ---
        CourseFull bscCS = new CourseFull("BSC-CS", "BSc Computer Science", Arrays.asList(year1));

        // --- 5. Create Students ---
        Student alice = new Student("243", "Alex");
        Student bob = new Student("244", "Billy");

        // --- 6. Create Teacher ---
        Lecturer drSmith = new Lecturer("T001", "Dr. Smith");

        // --- 8. Create Groups (students attending modules) ---
        Group groupCS101 = new Group(1, "Group1a", Arrays.asList(alice, bob), cs101.getModuleCode());

        LectureRoom csg001 = new LectureRoom("csg001", "LectureRoom.room", 150);

        // --- 9. Create TimeSlots ---
        TimeSlot ts1 = new TimeSlot( LocalTime.of(9,0), LocalTime.of(10,0), ma101,groupCS101, csg001, drSmith);
        TimeSlot ts2 = new TimeSlot(LocalTime.of(10,0), LocalTime.of(11,0), ph101, groupCS101, csg001, drSmith);

        // --- 10. Add TimeSlots to Timetables ---
        alice.getTimeTable().addTimeSlot("Monday", ts1);
        bob.getTimeTable().addTimeSlot("Monday", ts2);

        // --- 11. Print everything ---

        System.out.println("=== STUDENT TIMETABLES ===");
        System.out.println("Alice's timetable:");
        System.out.println(alice.getTimeTable());

        System.out.println("Bob's timetable:");
        System.out.println(bob.getTimeTable());

        System.out.println("=== MODULE GROUP INFO ===");

        Scanner sc = new Scanner(System.in);
        AdminCommandSession ad = new AdminCommandSession(sc, new InMemoryStudentService(), new InMemoryLecturerService());
        ad.run();

    }
}
