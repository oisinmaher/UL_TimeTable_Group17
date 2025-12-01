import adminPanel1.DataManager;
import adminPanel1.ReadData;
import adminPanel1.WriteData;
import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import rooms.LabRoom;
import rooms.LectureRoom;
import timetables.Group;
import timetables.TimeSlot;
import users.Student;
import users.Teacher;

public class Main {
    public static void main(String[] args) {

/*
        CourseFull courseFull = new CourseFull("LM121", "Computer Science");
        CourseYear courseYear = courseFull.addCourseYear("1");

        CourseSemester courseSemester = courseYear.addSemester("autumn");
        courseSemester.addNewModule("cs4023", "Operating systems");
        courseSemester.addNewModule("cs4401", "Database");

        CourseModule dataBaseModule = courseSemester.getModuleFromCode("cs4401");
        Group dataBaseGroup = dataBaseModule.getGroupAssigned(); //group created ; added empty group made above
        CourseModule opsModule = courseSemester.getModuleFromCode("cs4023");
        Group opsGroup = opsModule.getGroupAssigned();

        LectureRoom lectureRoom = new LectureRoom("CSG001", "lecture", 100);
        LabRoom labRoom = new LabRoom("CS3005B", "lab", 100);

        Teacher teacher = new Teacher("434", "Michael English");
        Teacher teacher1 = new Teacher("432", "Caoimhe");
        Student student = new Student("24377112", "Oisin", "LM121", "1");
        Student student2 = new Student("424234", "Yousef", "LM121", "1");
        Student student3 = new Student("4322545", "Alex", "LM121", "1");
        dataBaseGroup.addClassTimes("lab", "mon", "1500", labRoom.getRoomID(), teacher.getUserId()); //adding times
        opsGroup.addClassTimes("tut", "tues", "1205", labRoom.getRoomID(), teacher1.getUserId());
        opsGroup.addLectureTime("mon", "1200", lectureRoom.getRoomID(), teacher.getUserId());

        opsGroup.setTeacher("434");
        dataBaseGroup.setTeacher("432");

        TimeSlot dbSlot = dataBaseGroup.getTimeSlots("lab").get(0);
        TimeSlot opsSlot = opsGroup.getTimeSlots("tut").get(0);
        opsSlot.addStudent("24377112");
        opsSlot.addStudent("424234");
        dbSlot.addStudent("24377112");




        DataManager.students.put(student.getUserId(), student);
        DataManager.courseFulls.put(courseFull.getCode(), courseFull);
        DataManager.courseYears.put(courseYear.getYearId(), courseYear);
        DataManager.rooms.put(labRoom.getRoomID(), labRoom);
        DataManager.rooms.put(lectureRoom.getRoomID(), lectureRoom);
        DataManager.courseSemesters.put(courseSemester.getSemesterId(), courseSemester);
        DataManager.courseModules.put(dataBaseModule.getModuleCode(), dataBaseModule);
        DataManager.courseModules.put(opsModule.getModuleCode(),opsModule);
        DataManager.timeSlots.add(dbSlot);
        DataManager.groups.put("1B", dataBaseGroup);
        DataManager.timeSlots.add(opsSlot);
        DataManager.teachers.put(teacher.getUserId(), teacher);
        DataManager.teachers.put(teacher1.getUserId(), teacher1);
*/

        WriteData wd = new WriteData();
            wd.writeAll();

        ReadData rd = new ReadData();
            rd.importAll();

        //student.getUserTimeTable().printTimeTable();
        //DataManager.courseFulls.get("lm051").getCourseTimeTable().printTimeTable();

        //DataManager.students.get("424234").getUserTimeTable().printTimeTable();

    }
}
