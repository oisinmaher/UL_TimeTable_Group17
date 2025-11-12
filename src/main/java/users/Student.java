package src.main.java.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/**
 *
 */
public class Student extends User {
    int courseId;

    // testing objects (will remove likely)
    List<String[]> timeTable;
    Set<String> occupiedTimes;

    /**
     *
     * @param name
     * @param userId
     * @param courseId
     */
    public Student(String name, int userId, int courseId) {
        super(name, userId);
        this.courseId = courseId;

        // testing objects (will remove likely)
        timeTable = new ArrayList<>();
        occupiedTimes = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public int getCourseId(){
        return this.courseId;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return "Name: " + name + " ID: " + userId;
    }


    // test methods
    public void addClassToTimeTable(
            String modName, String classType, String roomNumber, String dateTime
    ){
        if(occupiedTimes.contains(dateTime)){
            System.out.println("Time is already taken");
            return;
        }
        String[] full = new String[]{modName, classType, roomNumber, dateTime};
        timeTable.add(full);
    }
    public void displayTimeTable(){
        for(String[] s : timeTable){
            System.out.println("Module Name " + s[0]); // OOP
            System.out.println("Class Type " + s[1]); // Tutorial
            System.out.println("Room Number " + s[2]); // CS1044
            System.out.println("Date & Time " + s[3]); // Tuesday 3PM
            System.out.println("------------------------------");
        }
    }
}
