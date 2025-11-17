package src.main.java.ui.cli;

import java.util.Scanner;
import src.main.java.users.Student;
/**
 * Represents the menu-based CLI session for a student user.
 * <p>
 * A {@code StudentMenuSession} provides a simple text-based menu that allows a
 * student to view their own stored details or log out of the system. This class
 * uses the {@link StudentService} interface to remain independent from the
 * underlying data storage implementation.
 * </p>
 */
public class StudentMenuSession implements UserSession {

    private final Scanner in;
    private final StudentService studentService;
    private final String studentId;

    /**
     * Constructs a new student menu session.
     *
     * @param in the input scanner used to read user choices
     * @param studentService the service used to retrieve student information
     * @param studentId the ID of the student currently logged in
     */
    public StudentMenuSession(Scanner in, StudentService studentService, String studentId) {
        this.in = in;
        this.studentService = studentService;
        this.studentId = studentId;
    }

    /**
     * Runs the student menu loop, allowing the student to:
     * <ul>
     *     <li>view their stored personal details</li>
     *     <li>log out and end the session</li>
     * </ul>
     * The method continues looping until the user selects the logout option.
     */
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
                    // Fetch and display student information
                    Student s = studentService.findStudentbyID(studentId);
                    if (s == null) {
                        System.out.println("Student not found in system.");
                    } else {
                        System.out.println("Your details: " + s);
                    }
                    break;

                case "2":
                    // Exit the session
                    running = false;
                    break;

                default:
                    // Handle invalid input
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
