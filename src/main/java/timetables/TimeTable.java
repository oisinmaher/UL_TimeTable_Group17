package src.main.java.timetables;

import src.main.java.users.User;
import src.main.java.programCourse.ProgramWithModule;
import java.util.*;

/**
 * This class is the timetable class
 * It's a map with days as key that contains TimeSlot objects as value
 * Only TimeSlots that have associated module at that time will be in the map
 * For example if a student didn't have any classes at 10am on Tuesday there would not be a Tuesday 10am timeslot in the map
 * Each instance of teacher and student will have a unique timetable object
 * Programs will also have timetables which contain every lecture lab and tutorial scheduled for a given Program
 *
 */
public class TimeTable {
    // Map Stores schedule of each day (Mon -> Fri)
    // TreeSet Contains Times in 24 hour clock 1400 is 2pm 0900 is 9 am
    Map<String, TreeSet<TimeSlot>> daySchedule;
    String[] daysOfWeek;
    // This variable will be used if its a persons timetable (e.g student/teacher)
    User user;
    // This variable will be used if it's a program timetable (e.g a course timetable consisting of all labs and lectures)
    ProgramWithModule program;


    /**
     * Timetable for an INDIVIDUAL user, won't have overlapping classes in same timeslot
     */
    public TimeTable(User user){
        // Init map - changed to LinkedHashMap to maintain order
        daySchedule = new HashMap<>();
        // Array of days of week (so it can loop through instead of hardcoding 5 put statements)
        this.daysOfWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        this.user = user;
        for(String day : daysOfWeek){
            // Ordered Set of Strings
            // (set[0] will be the time in 24 hours no decimal point 0900 = 9 am)
            TreeSet<TimeSlot> schedule = new TreeSet<>( Comparator.naturalOrder() );
            daySchedule.put(day, schedule);
        }
    }

    /**
     * Timetable for a whole program, can have overlapping classes in same timeslot
     * for example one group of students have a database lab, while another group have OOP lab at same time
     */
    public TimeTable(ProgramWithModule program){
        // Init map - changed to LinkedHashMap to maintain order
        daySchedule = new HashMap<>();
        // Array of days of week (so it can loop through instead of hardcoding 5 put statements)
        this.daysOfWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        this.program = program;
        for(String day : daysOfWeek){
            // Ordered Set of Strings
            // (set[0] will be the time in 24 hours no decimal point 0900 = 9 am)
            TreeSet<TimeSlot> schedule = new TreeSet<>( Comparator.naturalOrder() );
            daySchedule.put(day, schedule);
        }
    }

    /**
     *
     * @param day
     * @param slot
     */
    public void addTimeSlot(String day, TimeSlot slot){
        // check if timeslot is vacant
        if(daySchedule.get(day).contains(slot)){
            System.out.printf("This time slot is already occupied by\n" + daySchedule.get(day)+"\n");
        }
        else {
            // Adds module to a given day at given time on timetable
            daySchedule.get(day).add(slot);
        }
    }

    /**
     *
     * @return
     */
    public Map<String, TreeSet<TimeSlot>> getTimeTableMapping() {
        // Returns map that contains each day of timetable and it's associated timeslots
        return daySchedule;
    }
    public TreeSet<TimeSlot>  getTimeSlots(String day){
        // Returns
        return daySchedule.get(day);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(String day : daySchedule.keySet()){
            TreeSet<TimeSlot> slot = daySchedule.get(day);
            sb.append(day + "\n" + slot.toString()).append("\n");
        }
        return sb.toString();
    }
}

