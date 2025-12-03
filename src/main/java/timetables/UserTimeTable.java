package timetables;

import programCourse.CourseFull;
import users.Student;
import users.User;

import java.util.*;

/**
 * UserTable class.
 * Contains:
 *      - Map of dayTime code to a TimeSlot
 *      - Map of a users ID number to their corresponding timetable
 */
public class UserTimeTable {
    String courseCode;
    User user;
    Map<String, TimeSlot> classesAtTimes;
    final private static Map<String, UserTimeTable> allStudentsTimeTables = new HashMap<>();

    /**
     * Constructor that creates a users timetable and adds it to a map of everyone's timetables
     *
     * @param user a User object
     */
    public UserTimeTable(User user) {
        this.user = user;
        classesAtTimes = new HashMap<>();
        populateTimeMap(classesAtTimes);
        allStudentsTimeTables.put(user.getUserId(), this);
    }

    /**
     * This fills classesAtTimes map with every possible day and time (there's only 45)
     * This method is duplicated in CourseTimeTable
     *
     * @param classesAtTimes TreeMap that holds all times as key and timeslot objects as values in a list
     */
    private void populateTimeMap(Map<String, TimeSlot> classesAtTimes) {
        for (int day = 1; day <= 5; day++) {
            classesAtTimes.put(day + "_09", null);
            for (int time = 10; time <= 17; time++) {
                classesAtTimes.put(day + "_" + time, null);
            }
        }
    }

    /**
     * Searching through all timetables and retrieve the one with the corresponding ID number
     *
     * @param userId the users ID number
     * @return the timetable linked with the users ID number
     */
    public static UserTimeTable getUserTimetable(String userId) {
        return allStudentsTimeTables.get(userId);
    }

    /**
     * Checks if there is a Timeslot at a given time.
     *
     * @param dayTime a code that represents the day of the week and the time on the hour together
     * @return true if there is class on at the given time, false otherwise
     */
    public boolean containsTimeSlot(String dayTime) {
        return classesAtTimes.get(dayTime) != null;
    }

    /**
     * Adds a TimeSlot to a users timetable
     *
     * @param dayTime  a code that represents the day of the week and the time on the hour together
     * @param timeSlot a TimeSlot object
     * @throws IllegalArgumentException if the given time is already occupied by another TimeSlot
     */
    public void addTimeSlot(String dayTime, TimeSlot timeSlot) {
        if (containsTimeSlot(dayTime)) {
            throw new IllegalArgumentException("This user already has a module at this time");
        }
        classesAtTimes.put(dayTime, timeSlot);
    }

    /**
     * Gets all the timeslots from the users timetable
     *
     * @return list of timeslots
     */
    public List<TimeSlot> getTimeSlots() {
        return new ArrayList<>(classesAtTimes.values());
    }
/*
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int day = 1; day <= 5; day++) {
            String dayName = TimeSlot.getDayFromNumber(day);

            sb.append("====================================\n");
            sb.append("           ").append(dayName).append("\n");
            sb.append("====================================\n");

            for (int time = 9; time <= 17; time++) {
                String key = (time > 9) ? day + "_" + time : day + "_09";
                String timeLabel = TimeSlot.getTimeFromShortened(time);
                TimeSlot slot = classesAtTimes.get(key);

                sb.append(String.format("%-10s | ", timeLabel));

                if (slot == null) {
                    sb.append("Free Period\n");
                } else {
                    sb.append(slot.toString()).append("\n");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
*/
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();

    // Header row
    sb.append("Time\t");
    for (int day = 1; day <= 5; day++) {
        String dayName = TimeSlot.getDayFromNumber(day); // e.g. "Monday"
        sb.append(dayName).append("\t");
    }
    sb.append("\n");

    // For each time from 09:00 to 17:00
    for (int time = 9; time <= 17; time++) {
        // First column: the time label (e.g. "09:00")
        String timeLabel = TimeSlot.getTimeFromShortened(time);
        sb.append(timeLabel).append("\t");

        // Then one column per day
        for (int day = 1; day <= 5; day++) {
            String key;

            // keys are like "1_09", "1_10", ... "5_17"
            if (time == 9) {
                key = day + "_09";
            } else {
                key = day + "_" + time;
            }

            TimeSlot slot = classesAtTimes.get(key);

            if (slot == null) {
                sb.append("Free").append("\t");
            } else {
                // You can change this to show whatever you want from TimeSlot
                sb.append(slot.toString()).append("\t");
            }
        }

        sb.append("\n");
    }

    return sb.toString();
}


