package ui.cli1;

import users.Teacher;
import java.util.Scanner;
import ui.cli1.TeacherService;

public class TeacherMenuSession implements UserSession {

    private final Scanner in;
    private final TeacherService teacherService;
    private final String teacherId;
    private final TimetableService timetableService;

    public TeacherMenuSession(Scanner in, TeacherService teacherService, String teacherId, TimetableService timetableService) {
        this.in = in;
        this.teacherService = teacherService;
        this.teacherId = teacherId;
        this.timetableService = timetableService; 
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Teacher Menu ===");
            System.out.println("1. View my details");
            System.out.println("2. View my timetable");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            String choice = in.nextLine().trim();

            switch (choice) {
                case "1":
                    Teacher l = teacherService.findTeacherById(teacherId);
                    if (l == null) {
                        System.out.println("teacher not found in system.");
                    } else {
                        System.out.println("Your details: " + l);
                    }
                    break;
                case "2":
                    System.out.println();
                    System.out.println(timetableService.getTeacherTimetableAsString(teacherId));
                case "3": 
                running = false; 
                break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
