package src.main.java.adminPanel;
import src.main.java.users.*;
import src.main.java.rooms.*;
import src.main.java.timetables.*;
import src.main.java.programCourse.*;
import src.main.java.modules.*;
import java.util.ArrayList;
import java.util.List;

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

    List<Student> studentsList = new ArrayList<>();
    List<Teacher> teachersList = new ArrayList<>();
    List<ProgramSemester> programSemList = new ArrayList<>();
    List<ProgramYear> programYearsList = new ArrayList<>();
    List<Room> roomsList = new ArrayList<>();
    List<TimeSlot> timeSlotList = new ArrayList<>();
    List<TimeTable> timeTableList = new ArrayList<>();
    private DataManager() {
    }
    public static DataManager getInstance() {
        return INSTANCE;
    }
}
