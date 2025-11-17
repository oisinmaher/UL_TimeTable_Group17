package ui.cli1;

import java.util.Arrays;
import java.util.Scanner;
import users.*;

public class AdminCommandSession implements UserSession {

    private final Scanner in;
    private final StudentService studentService;
    private final LecturerService lecturerService;

    public AdminCommandSession(Scanner in, StudentService studentService, LecturerService lecturerService) {
        this.in = in;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
    }

    @Override
    public void run() {
        System.out.println("\n=== Admin Command Shell ===");
        System.out.println("Type 'help' to see available commands.");

        boolean running = true;
        while (running) {
            System.out.print("admin> ");
            String line = in.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase();
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
//            System.out.println("Arguments are " + Arrays.toString(args));

            switch (cmd) {
                case "add-student":
                    cmdAddStudent(args);
                    break;
                case "remove-student":
                    cmdRemoveStudent(args);
                    break;
                case "update-student":
                    cmdUpdateStudent(args);
                    break;
                case "add-lecturer":
                    cmdAddLecturer(args);
                    break;
                case "update-lecturer":
                    cmdUpdateLecturer(args);
                    break;
                case "list-students":
                    cmdListStudents();
                    break;
                case "list-lecturers":
                    cmdListLecturers();
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list.");
            }
        }
    }

    // ===== Commands =====

    private void cmdAddStudent(String[] args) {
//        System.out.println("Arguments in function are " + Arrays.toString(args));
        if (args.length < 2) {
            System.out.println("Usage: add-student <id> <name>");
            return;
        }
        String id = args[0];
        String name = args[1];
        for(int i = 2; i < args.length; i++){
            name = name + " " + args[i];
        }

        boolean ok = studentService.addStudent(id, name);
        if (ok) {
            System.out.println("Student added: " + id);
        } else {
            System.out.println("Student with id " + id + " already exists.");
        }
    }

    private void cmdRemoveStudent(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: remove-student <id>");
            return;
        }
        String id = args[0];
        boolean ok = studentService.removeStudent(id);
        if (ok) {
            System.out.println("Student removed: " + id);
        } else {
            System.out.println("No student with id " + id + " found.");
        }
    }

    private void cmdUpdateStudent(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: update-student <id> <field> <newValue>");
            return;
        }
        String id = args[0];
        String field = args[1];
        String newValue = args[2];
        for(int i = 3; i < args.length; i++){
            newValue = newValue + " " + args[i];
        }
        boolean ok = studentService.updateStudentField(id, field, newValue);
        if (ok) {
            System.out.println("Student " + id + " updated (" + field + ").");
        } else {
            System.out.println("Could not update student. Check id/field.");
        }
    }

    private void cmdAddLecturer(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: add-lecturer <id> <fullName>");
            return;
        }
        String id = args[0];
        String name = args[1];
        for(int i = 2; i < args.length; i++){
            name = name + " " + args[i];
        }
        boolean ok = lecturerService.addLecturer(id, name);
        if (ok) {
            System.out.println("Lecturer added: " + id);
        } else {
            System.out.println("Lecturer with id " + id + " already exists.");
        }
    }

    private void cmdUpdateLecturer(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: update-lecturer <id> <field> <newValue>");
            System.out.println("Fields: fullName");
            return;
        }
        String id = args[0];
        String field = args[1];
        String newValue = args[2];
        for(int i = 3; i < args.length; i++){
            newValue = newValue + " " + args[i];
        }

        boolean ok = lecturerService.updateLecturerField(id, field, newValue);
        if (ok) {
            System.out.println("Lecturer " + id + " updated (" + field + ").");
        } else {
            System.out.println("Could not update lecturer. Check id/field.");
        }
    }

    private void cmdListStudents() {
        System.out.println("Students:");
        for (Student s : studentService.getAllStudents()) {
            System.out.println("  " + s);
        }
    }

    private void cmdListLecturers() {
        System.out.println("Lecturers:");
        for (Lecturer l : lecturerService.getAllLecturers()) {
            System.out.println("  " + l);
        }
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  add-student <id> <fullName>");
        System.out.println("  remove-student <id>");
        System.out.println("  update-student <id> <field> <newValue>");
        System.out.println("  add-lecturer <id> <fullName>");
        System.out.println("  update-lecturer <id> <field> <newValue>");
        System.out.println("  list-students");
        System.out.println("  list-lecturers");
        System.out.println("  help");
        System.out.println("  exit");
    }
}
