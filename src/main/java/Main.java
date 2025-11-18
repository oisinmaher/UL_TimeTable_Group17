

import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import rooms.LectureRoom;
import timetables.Group;
import timetables.TimeSlot;
import users.Lecturer;
import users.Student;

import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {


        // --- 1. Create Modules ---
        CourseModule cs101 = new CourseModule("Introduction to CS", "CS101");
        CourseModule ma101 = new CourseModule("Calculus I", "MA101");
        CourseModule ph101 = new CourseModule("Physics I", "PH101");

        // --- 2. Create Semesters ---
        CourseSemester autumn = new CourseSemester("Autumn");
        CourseSemester spring = new CourseSemester("Spring");

        // --- 3. Create Course Year ---
        CourseYear year1 = new CourseYear("3");

        // --- 4. Create Course ---
        CourseFull bscCS = new CourseFull("BSC-CS", "BSc Computer Science");

        // --- 5. Create Students ---
        Student alice = new Student("123","Alex","CS4013","2");
        Student bob = new Student("124","Bob","CS4013","3");

        // --- 6. Create Teacher ---
        Lecturer drSmith = new Lecturer("T001", "Dr. Smith");

        // --- 8. Create Groups (students attending modules) ---
        Group groupCS101 = new Group(cs101);

        LectureRoom csg001 = new LectureRoom("csg001", "LectureRoom.room", 150);

        // --- 9. Create TimeSlots ---
        TimeSlot ts1 = new TimeSlot("Monday", "0900", cs101);
        TimeSlot ts2 = new TimeSlot("Monday", "1000", ma101);


        // --- 11. Print everything ---


        //Scanner sc = new Scanner(System.in);
        //AdminCommandSession ad = new AdminCommandSession(sc, new InMemoryStudentService(), new InMemoryLecturerService());
        //ad.run();

    }
}
