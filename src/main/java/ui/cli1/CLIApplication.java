package ui.cli1;

import java.util.Scanner;

public class CLIApplication {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        StudentService studentService = new InMemoryStudentService();
        LecturerService lecturerService = new InMemoryLecturerService();
        CourseFullService courseFullService = new InMemoryCourseFullService();
        CourseYearService courseYearService = new InMemoryCourseYearService();

        boolean running = true;

        while (running) {
            System.out.println("\n=== UL Timetabling System (Users Focus) ===");
            System.out.println("1. Login as student");
            System.out.println("2. Login as lecturer");
            System.out.println("3. Login as admin");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = in.nextLine().trim();

            UserSession session = null;

            switch (choice) {
                case "1":
                    System.out.print("Enter student id: ");
                    String studentId = in.nextLine().trim();
                    session = new StudentMenuSession(in, studentService, studentId);
                    break;

                case "2":
                    System.out.print("Enter lecturer id: ");
                    String lecturerId = in.nextLine().trim();
                    session = new LecturerMenuSession(in, lecturerService, lecturerId);
                    break;

                case "3":
                    System.out.print("Enter admin password: ");
                    String pwd = in.nextLine().trim();
                    // placeholder password – change later
                    if (!"admin".equals(pwd)) {
                        System.out.println("Invalid admin password.");
                        break;
                    }
                    session = new AdminCommandSession(in, studentService, lecturerService, courseFullService, courseYearService);
                    break;

                case "4":
                    running = false;
                    continue;

                default:
                    System.out.println("Invalid option, try again.");
                    continue;
            }

            if (session != null) {
                session.run();
            }
        }

        System.out.println("Goodbye.");
        in.close();
    }
}
