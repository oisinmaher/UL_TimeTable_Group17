package src.main.java.ui.cli;

import src.main.java.users.Lecturer;

import java.util.Scanner;

public class LecturerMenuSession implements UserSession {

    private final Scanner in;
    private final LecturerService lecturerService;
    private final String lecturerId;

    public LecturerMenuSession(Scanner in,LecturerService lecturerService,String lecturerId) {
        this.in = in;
        this.lecturerService = lecturerService;
        this.lecturerId = lecturerId;
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Lecturer Menu ===");
            System.out.println("1. View my details");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");

            String choice = in.nextLine().trim();

            switch (choice) {
                case "1":
                    Lecturer l = lecturerService.findLecturerById(lecturerId);
                    if (l == null) {
                        System.out.println("Lecturer not found in system.");
                    } else {
                        System.out.println("Your details: " + l);
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
