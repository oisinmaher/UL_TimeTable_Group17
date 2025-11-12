package src.main.java.timetables;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TimeTable {
    // Map Stores schedule of each day (Mon -> Fri)
    Map<String, TreeSet<String[]>> daySchedule;
    public TimeTable(){
        // Init map
        daySchedule = new HashMap<>();
        // Array of days of week (so it can loop through instead of hardcoding 5 put statements)
        String[] daysOfWeek = new String[]{
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
        };
        // Creates new schedule for each day
        for(String day : daysOfWeek){
            // Ordered Set of Strings
            // (set[0] will be the time in 24 hours no decimal point 0900 = 9 am)
            TreeSet<String[]> schedule = new TreeSet<>(
                    (a,b) -> a[0].compareTo(b[0])
            );
            daySchedule.put(day, schedule);
        }
    }
}

