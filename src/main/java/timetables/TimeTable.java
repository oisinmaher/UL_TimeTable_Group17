package src.main.java.timetables;

import java.util.*;

/**
 *
 */
public class TimeTable {
    // Map Stores schedule of each day (Mon -> Fri)
    Map<String, TreeSet<TimeSlot>> daySchedule;

    /**
     *
     */
    public TimeTable(){
        // Init map - changed to LinkedHashMap to maintain order
        daySchedule = new LinkedHashMap<>();
        // Array of days of week (so it can loop through instead of hardcoding 5 put statements) - changed to ArrayList to maintain order
        List<String> daysOfWeek = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));


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
        daySchedule.get(day).add(slot);
    }

    /**
     *
     * @return
     */
    public Map<String, TreeSet<TimeSlot>> getDaySchedule() {
        return daySchedule;
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

