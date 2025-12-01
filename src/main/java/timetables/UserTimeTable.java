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

    /**
     * Prints Timetable for this user order
     */
        @Override
        public String toString() {
            StringBuilder sb2 = new StringBuilder();

            for (int day = 1; day <= 5; day++) {
                String dayName = TimeSlot.getDayFromNumber(day);

                sb2.append("====================================\n");
                sb2.append("           ").append(dayName).append("\n");
                sb2.append("====================================\n");

                for (int time = 9; time <= 17; time++) {
                    dayName = time > 9 ? day + "_" + time : day + "_09";
                    String timeFull = TimeSlot.getTimeFromShortened(time);
                    TimeSlot timeSlot = classesAtTimes.get(dayName);
                    System.out.println("----------------------");
                    System.out.println(timeFull);
                    if (timeSlot == null) {
                        System.out.println("Free Period");
                    } else {
                        sb2.append(sb2.toString()).append("\n");
                    }
                }

                sb2.append("\n");
            }

            return sb2.toString();
        }


}
