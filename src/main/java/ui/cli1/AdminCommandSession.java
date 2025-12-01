package ui.cli1;

import adminPanel1.WriteData;
import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import rooms.LabRoom;
import rooms.LectureRoom;
import rooms.Room;
import timetables.Group;
import timetables.TimeSlot;
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
            Scanner in, StudentService studentService, TeacherService teacherService,
            CourseFullService courseFullService, CourseYearService courseYearService) {
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
                case "add-course-semester":
                    cmdAddCourseSemester(args);
                    break;
                case "create-course-module":
                    cmdCreateCourseModule(args);
                    break;
                case "add-existing-module":
                    cmdAddExistingModule(args);
                    break;
                case "add-timeslot":
                    cmdAddTimeslot(args);
                    break;
                case "add-student-to-timeslot":
                    cmdAddStudentToTime(args);
                    break;
                case "create-room":
                    cmdCreateRoom(args);
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
                case "save-exit":
                    WriteData wd = new WriteData();
                    wd.writeAll();
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list.");
            }
        }
    }


    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  add-student <id> <fullName> <course> <year>");
        System.out.println("  remove-student <id>");
        System.out.println("  update-student <id> <field> <newValue>");
        System.out.println("  add-teacher <id> <fullName>");
        System.out.println("  update-teacher <id> <field> <newValue>");
        System.out.println("  add-course-full <courseCode> <courseName>");
        System.out.println("  add-course-year <courseCode> <year>");
        System.out.println("  add-course-semester <courseCode> <courseYear> <semester>");
        System.out.println("  create-course-module <courseCode> <courseYear> <semester> <moduleCode> <moduleName>");
        System.out.println("  add-existing-module <courseCode> <courseYear> <semester> <moduleCode>");
        System.out.println("  add-timeslot <moduleCode> <day> <time> <room> <teacher>");
        System.out.println("  add-student-to-timeslot <moduleCode> <day> <time> <studentId>");
        System.out.println("  create-room <roomID> <roomType> <maxCapacity>");
        System.out.println("  list-courses");
        System.out.println("  list-course-years <courseCode>");
        System.out.println("  list-students");
        System.out.println("  list-teachers");
        System.out.println("  help");
        System.out.println("  save-exit");
    }

    // ===== Students & Teachers Commands =====
    private void cmdAddStudent(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: add-student <id> <name> <course> <year>");
            return;
        }
        String id = args[0];
        String name = args[1];
        String course = args[args.length - 2];
        String year = args[args.length - 1];
        for (int i = 2; i < args.length - 2; i++) name += " " + args[i];
        boolean ok = studentService.addStudent(id, name, course, year);
        System.out.println(ok ? "Student added: " + id : "Student with id " + id + " already exists.");
    }

    private void cmdRemoveStudent(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: remove-student <id>");
            return;
        }
        boolean ok = studentService.removeStudent(args[0]);
        System.out.println(ok ? "Student removed: " + args[0] : "No student with id " + args[0] + " found.");
    }

    private void cmdUpdateStudent(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: update-student <id> <field> <newValue>");
            return;
        }
        String id = args[0];
        String field = args[1];
        String newValue = args[2];
        for (int i = 3; i < args.length; i++) newValue += " " + args[i];
        boolean ok = studentService.updateStudentField(id, field, newValue);
        System.out.println(ok ? "Student " + id + " updated (" + field + ")." : "Could not update student. Check id/field.");
    }

    private void cmdAddTeacher(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: add-teacher <id> <fullName>");
            return;
        }
        String id = args[0];
        String name = args[1];
        for (int i = 2; i < args.length; i++) name += " " + args[i];
        boolean ok = teacherService.addTeacher(id, name);
        System.out.println(ok ? "Teacher added: " + id : "Teacher with id " + id + " already exists.");
    }

    private void cmdUpdateTeacher(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: update-teacher <id> <field> <newValue>");
            return;
        }
        String id = args[0];
        String field = args[1];
        String newValue = args[2];
        for (int i = 3; i < args.length; i++) newValue += " " + args[i];
        boolean ok = teacherService.updateTeacherField(id, field, newValue);
        System.out.println(ok ? "Teacher " + id + " updated (" + field + ")." : "Could not update teacher. Check id/field.");
    }

    // ===== Courses =====
    private void cmdAddCourseFull(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: add-course-full <courseCode> <courseName>");
            return;
        }
        String courseCode = args[0];
        String courseName = args[1];
        for (int i = 2; i < args.length; i++) courseName += " " + args[i];
        boolean ok = courseFullService.createCourse(courseCode, courseName);
        System.out.println(ok ? "Successfully added course " + courseCode + ": " + courseName : "Unsuccessful, check input.");
    }

    private void cmdAddCourseYear(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: add-course-year <courseCode> <year>");
            return;
        }
        boolean ok = courseYearService.addYear(args[0], args[1]);
        System.out.println(ok ? "Successfully added year " + args[1] + " to " + args[0] : "Unsuccessful, check input.");
    }

    private void cmdAddCourseSemester(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: add-course-semester <courseCode> <courseYear> <semester>");
            return;
        }
        String courseCode = args[0];
        String year = args[1];
        String semester = args[2];
        CourseYear cy = CourseFull.getCourseFromCode(courseCode).getCourseYear(year);
        if (cy == null) {
            System.out.println("Course year not found.");
            return;
        }
        if (cy.hasSemester(semester)) {
            System.out.println("Semester already exists.");
            return;
        }
        cy.addSemester(semester);
        System.out.println("Semester " + semester + " added to " + courseCode + " " + year);
    }

    private void cmdCreateCourseModule(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: create-course-module <courseCode> <courseYear> <semester> <moduleCode> <moduleName>");
            return;
        }
        String courseCode = args[0];
        String year = args[1];
        String semester = args[2];
        String moduleCode = args[3];
        String moduleName = args[4];
        for (int i = 5; i < args.length; i++) moduleName += " " + args[i];

        CourseSemester cs = CourseFull.getCourseFromCode(courseCode)
                .getCourseYear(year).getSemester(semester);
        if (cs == null) {
            System.out.println("Semester not found.");
            return;
        }
        cs.addNewModule(moduleCode, moduleName);
        System.out.println("Module " + moduleCode + " added to " + courseCode + " " + year + " " + semester);
    }

    private void cmdAddExistingModule(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: add-existing-module <courseCode> <courseYear> <semester> <moduleCode>");
            return;
        }
        String courseCode = args[0];
        String year = args[1];
        String semester = args[2];
        String moduleCode = args[3];

        CourseModule module = CourseModule.getModuleFromCode(moduleCode);
        if (module == null) {
            System.out.println("Module not found.");
            return;
        }

        CourseSemester cs = CourseFull.getCourseFromCode(courseCode)
                .getCourseYear(year).getSemester(semester);
        if (cs.containsModule(moduleCode)) {
            System.out.println("Module already exists in this semester.");
            return;
        }
        cs.addExistingModule(moduleCode);
        System.out.println("Module " + moduleCode + " added to " + courseCode + " " + year + " " + semester);
    }

    private void cmdAddTimeslot(String[] args) {
        if (args.length != 6) {
            System.out.println("Usage: add-timeslot <moduleCode> <type> <day> <time> <room> <teacher>");
            return;
        }
        String moduleCode = args[0].toLowerCase();
        String type = args[1].toLowerCase();
        String day = args[2];
        String time = args[3];
        String roomId = args[4];
        String teacherId = args[5];

        // Check if module exists
        CourseModule module = CourseModule.getModuleFromCode(moduleCode);
        if (module == null) {
            System.out.println("Module does not exist.");
            return;
        }

        // Get or create group for this module
        Group group = Group.getAllGroups().get(moduleCode);
        if (group == null) {
            // Assuming you want a default list of courses for the group
            group = new Group(module, List.of());
            System.out.println("Created new group for module " + moduleCode);
        }

        try {
            if (type.equals("lecture")) {
                group.addLectureTime(day, time, roomId, teacherId);
            } else if (type.equals("lab") || type.equals("tutorial") || type.equals("tut")) {
                group.addClassTimes(type, day, time, roomId, teacherId);
            } else {
                System.out.println("Invalid type. Use lecture, lab, or tutorial.");
                return;
            }
            System.out.println("Timeslot added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding timeslot: " + e.getMessage());
        }
    }
    //    / CourseFull, CourseYear, CourseSemester, ModuleCode, day, time, studentId
    private void cmdAddStudentToTime(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: add-student-to-timeslot <moduleCode> <day> <time> <studentId>");
            return;
        }
        String moduleCode = args[0];
        String day = args[1];
        String time = args[2];
        String studentId = args[3];
        CourseModule module = CourseModule.getModuleFromCode(moduleCode);
        Group group = module.getGroupAssigned();
        TimeSlot ts = group.getTimeSlotFromTime(day, time);
        ts.addStudent(studentId);
    }


    private void cmdCreateRoom(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: create-room <roomID> <roomType> <maxCapacity>");
            return;
        }
        String roomID = args[0];
        String roomType = args[1];
        int maxCapacity = 0;
        if (args.length == 3) {
            try {
                maxCapacity = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid maxCapacity.");
                return;
            }
        }
        try {
            if (roomType.equalsIgnoreCase("lab")) {
                new LabRoom(roomID, roomType, maxCapacity);
            } else if (roomType.equalsIgnoreCase("lecture")) {
                new LectureRoom(roomID, roomType, maxCapacity);
            } else {
                System.out.println("Invalid room type.");
                return;
            }
            System.out.println("Room created: " + roomID + " (" + roomType + ")");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // ===== List Commands =====
    private void cmdListCourses() {
        System.out.println("All Courses on System:");
        for (String course : courseFullService.listAllCourses()) System.out.println(course);
    }

    private void cmdListCourseYears(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: list-course-years <courseCode>");
            return;
        }
        List<String> courseYears = courseFullService.listCourseYears(args[0]);
        if (courseYears.isEmpty()) {
            System.out.println(args[0] + " does not have any years.");
            return;
        }
        System.out.println(String.join(", ", courseYears));
    }

    private void cmdListStudents() {
        System.out.println("Students:");
        for (Student s : studentService.getAllStudents()) System.out.println("  " + s);
    }

    private void cmdListTeachers() {
        System.out.println("Teachers:");
        for (Teacher t : teacherService.getAllTeachers()) System.out.println("  " + t);
    }
}
