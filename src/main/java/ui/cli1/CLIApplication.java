package ui.cli1;

import timetables.UserTimeTable;
//import data.StudentData;
//import data.TeacherData;

import java.util.Scanner;

/**
 * Entry point for the hybrid CLI of the timetabling system.
 */
public class CLIApplication {
//
//    public static void main(String[] args) {
//
//        Scanner in = new Scanner(System.in);
//
//        try {
//            // --- Data layer: StudentData & TeacherData handle CSV internally ---
//            StudentData studentData = new StudentData();
//            TeacherData teacherData = new TeacherData();
//
//            // --- Service layer: CSV-backed services ---
//            StudentService studentService = new CSVStudentService(studentData);
//            TeacherService teacherService = new CSVTeacherService(teacherData);
//
//            // --- Timetable layer: already prepared in the Timetable class (TreeMaps) ---
//            UserTimeTable domainTimetable = new UserTimeTable(null);     // already populated. Oisin please change later
//            TimetableService timetableService = new TimetableWrapperService(domainTimetable);
//
//            boolean running = true;
//
//            while (running) {
//                System.out.println("\n=== UL Timetabling System (Users Focus) ===");
//                System.out.println("1. Login as student");
//                System.out.println("2. Login as teacher");
//                System.out.println("3. Login as admin");
//                System.out.println("4. Exit");
//                System.out.print("Choose an option: ");
//
//                String choice = in.nextLine().trim();
//                UserSession session = null;
//
//                switch (choice) {
//                    case "1":
//                        System.out.print("Enter student id: ");
//                        String studentId = in.nextLine().trim();
//                        session = new StudentMenuSession(in, studentService, timetableService, studentId);
//                        break;
//
//                    case "2":
//                        System.out.print("Enter teacher id: ");
//                        String TeacherId = in.nextLine().trim();
//                        session = new TeacherMenuSession(in, teacherService, TeacherId, timetableService);
//                        break;
//
//                    case "3":
//                        System.out.print("Enter admin password: ");
//                        String pwd = in.nextLine().trim();
//                        if (!"admin".equals(pwd)) {
//                            System.out.println("Invalid admin password.");
//                            break;
//                        }
//                        session = new AdminCommandSession(in, studentService, teacherService);
//                        break;
//
//                    case "4":
//                        running = false;
//                        continue;
//
//                    default:
//                        System.out.println("Invalid option, try again.");
//                        continue;
//                }
//
//                if (session != null) {
//                    session.run();
//                }
//            }
//
//        } catch (Exception e) {
//            System.err.println("Fatal error initialising CLI: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            in.close();
//        }
//
//        System.out.println("Goodbye.");
//    }
}
