package ui.cli1;
import users.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdminCommandSession implements UserSession {

    private final Scanner in;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseFullService courseFullService;
    private final CourseYearService courseYearService;

    public AdminCommandSession(
            Scanner in, StudentService studentService, TeacherService teacherService, CourseFullService courseFullService,
            CourseYearService courseYearService) {
        this.in = in;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseFullService = courseFullService;
        this.courseYearService = courseYearService;
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
                case "add-teacher":
                    cmdAddTeacher(args);
                    break;
                case "update-teacher":
                    cmdUpdateTeacher(args);
                    break;
                case "add-course-full":
                    cmdAddCourseFull(args);
                    break;
                case "add-course-year":
                    cmdAddCourseYear(args);
                    break;
                case "list-courses":
                    cmdListCourses();
                    break;
                case "list-course-years":
                    cmdListCourseYears(args);
                    break;
                case "list-students":
                    cmdListStudents();
                    break;
                case "list-teachers":
                    cmdListTeachers();
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
    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  add-student <id> <fullName> <course> <year>");
        System.out.println("  remove-student <id>");
        System.out.println("  update-student <id> <field> <newValue>");
        System.out.println("  add-teacher <id> <fullName>");
        System.out.println("  update-teacher <id> <field> <newValue>");
        System.out.println("  add-course-full <courseCode> <courseName>");
        System.out.println("  add-course-year <courseCode> <year>");
        System.out.println("  list-courses");
        System.out.println("  list-course-years <courseCode>");
        System.out.println("  list-students");
        System.out.println("  list-teachers");
        System.out.println("  help");
        System.out.println("  exit");
    }
    private void cmdAddStudent(String[] args) {
//        System.out.println("Arguments in function are " + Arrays.toString(args));
        if (args.length < 4) {
            System.out.println("Usage: add-student <id> <name>");
            return;
        }
        String id = args[0];
        String name = args[1];
        String course = args[args.length-2];
        String year = args[args.length-1];
        for(int i = 2; i < args.length-2; i++){
            name = name + " " + args[i];
        }

        boolean ok = studentService.addStudent(id, name, course, year);
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

    private void cmdAddTeacher(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: add-teacher <id> <fullName>");
            return;
        }
        String id = args[0];
        String name = args[1];
        for(int i = 2; i < args.length; i++){
            name = name + " " + args[i];
        }
        boolean ok = teacherService.addTeacher(id, name);
        if (ok) {
            System.out.println("teacher added: " + id);
        } else {
            System.out.println("teacher with id " + id + " already exists.");
        }
    }

    private void cmdUpdateTeacher(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: update-teacher <id> <field> <newValue>");
            System.out.println("Fields: fullName");
            return;
        }
        String id = args[0];
        String field = args[1];
        String newValue = args[2];
        for(int i = 3; i < args.length; i++){
            newValue = newValue + " " + args[i];
        }

        boolean ok = teacherService.updateTeacherField(id, field, newValue);
        if (ok) {
            System.out.println("Teacher " + id + " updated (" + field + ").");
        } else {
            System.out.println("Could not update teacher. Check id/field.");
        }
    }
    private void cmdAddCourseFull(String[] args){
        if(args.length < 2){
            System.out.println("Usage: add-course-full <courseCode> <courseName>");
            return;
        }
        String courseCode = args[0];
        String courseName = args[1];
        for(int i = 2; i < args.length; i++){
            courseName += " " + args[i];
        }
        boolean ok = courseFullService.createCourse(courseCode, courseName);
        if(ok) {
            System.out.println("Successfully added course " + courseCode + ": " + courseName);
        }
        else{
            System.out.println("Unsuccessful, read above for help and ensure the right syntax");
        }
    }
    private void cmdAddCourseYear(String[] args){
        if(args.length != 2){
            System.out.println("Usage: add-course-year <courseCode> <year>");
            return;
        }
        String courseCode = args[0];
        String courseYear = args[1];
        boolean ok = courseYearService.addYear(courseCode, courseYear);
        if(ok){
            System.out.println("Successfully added year " + courseYear + " to " + courseCode);
        }
        else{
            System.out.println("Unsuccessful, read above for help and ensure the right syntax");
        }
    }
    private void cmdListCourses(){
        System.out.println("All Courses on System:");
        for(String course : courseFullService.listAllCourses()){
            System.out.println(course);
        }
    }
    private void cmdListCourseYears(String[] args){
        if(args.length != 1){
            System.out.println("Usage: list-course-years <courseCode>");
        }
        String courseCode = args[0];
        List<String> courseYears = courseFullService.listCourseYears(courseCode);
        if(courseYears.isEmpty()){
            System.out.println(courseCode + " does not have any years, you must create them");
        }
        else{
            System.out.print(courseYears.get(0));
            for(int i = 1; i < courseYears.size(); i++){
                System.out.print(", " + courseYears.get(i));
            }
            System.out.println();
        }
    }
    private void cmdListStudents() {
        System.out.println("Students:");
        for (Student s : studentService.getAllStudents()) {
            System.out.println("  " + s);
        }
    }

    private void cmdListTeachers() {
        System.out.println("Teachers:");
        for (Teacher t : teacherService.getAllTeachers()) {
            System.out.println("  " + t);
        }
    }

}
