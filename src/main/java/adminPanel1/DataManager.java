package adminPanel1;
import programCourse.*;
import rooms.*;
import timetables.*;
import users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is a singleton class, meaning it will only have a single instance of it.
 * It will be created automatically on the start-up of program
 * This class will hold an array list of all instances of objects
 * For example it will hold a list of all students, teachers, modules etc.
 * This is needed mostly for the reading and writing of csv files
 * Nearly all other objects will be using this in their constructor methods
 */
public class DataManager {
    // this automatically initializes DataManager on start up
    // (sort of, it only initializes when another class tries to use it)
    private static final DataManager INSTANCE = new DataManager();

        public static Map<String, Room> rooms = Room.getAllRooms();
        public static Map<String, Student> students = Student.getAllStudentsEnrolled();
        public static Map<String, Teacher> teachers = Teacher.getAllTeachers();
        public static Map<String, Group> groups = Group.getAllGroups();
        public static Map<String, CourseFull> courseFulls = CourseFull.getAllCourseFulls();



    private DataManager() {
    }
    public static DataManager getInstance() {
        return INSTANCE;
    }
}
