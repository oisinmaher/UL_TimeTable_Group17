package src.main.java.timetables;

import src.main.java.programCourse.CourseFull;
import src.main.java.users.User; 
import java.util.*;

/**
 * This class is the timetable class
 * It's a map with Days And Time (string format) as key that contains Group objects as value
 * Only TimeSlots that have associated group at that time will be in the map
 * For example if a student didn't have any classes at 10am on Tuesday there would not be a Tuesday 10am String in the map
 * Each instance of teacher and student will have a unique timetable object
 *
 */
public class UserTimeTable {

    // Set Contains day of week in 3characters and Times in 24 hour
    // wed1600 is wednesday 4pm, fri0900 is friday 9 am
    Map<String, Group> schedule = new HashMap<>();
    String[] daysOfWeek;
    // This variable will be used if its a persons timetable (e.g student/teacher)
    User user;
    // This variable will be used if it's a program timetable (e.g a course timetable consisting of all labs and lectures)
    CourseFull course;


    /**
     * Timetable for an INDIVIDUAL user, won't have overlapping classes in same time
     */
    public UserTimeTable(User user){
        schedule = new HashMap<>();
        // Array of days of week (so it can loop through instead of hardcoding 5 put statements)
        this.daysOfWeek = new String[]{"mon", "tue", "wed", "thu", "fri"};
        this.user = user;

    }

    public Map<String, Group> getTimeTableMapping() {
        // Returns map that contains each day of timetable and it's associated groups
        return schedule;
    }
    public boolean checkTime(String time){
        // checks if a time is occupied
        return schedule.containsKey(time);
    }
    public Group getGroupAtTime(String time){
        // gets the group a student in a certain time
        return schedule.get(time);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(String dayTime : schedule.keySet()){
            sb.append(dayTime + schedule.get(dayTime));
        }
        return sb.toString();
    }
}

