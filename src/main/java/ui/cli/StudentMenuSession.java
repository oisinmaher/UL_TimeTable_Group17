package src.main.java.ui.cli;

import java.util.Scanner;

public class StudentMenuSession implements UserSession {

    private final Scanner in;
    private final StudentService studentService;
    private final String studentId;

    public StudentMenuSession(Scanner in,
                              StudentService studentService,
                              String studentId) {
        this.in = in;
        this.studentService = studentService;
        this.studentId = studentId;
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View my details");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");

            String choice = in.nextLine().trim();

            switch (choice) {
                case "1":
                    Student s = studentService.findStudentbyID(studentId);
                    if (s == null) {
                        System.out.println("Student not found in system.");
                    } else {
                        System.out.println("Your details: " + s);
                    }
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
