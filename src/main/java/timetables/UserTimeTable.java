package timetables;

import programCourse.CourseFull;
import users.Student;
import users.User;

import java.util.*;

public class UserTimeTable {
    String courseCode;
    User user;
    TreeMap<String, TimeSlot> classesAtTimes;
    final private static Map<String, UserTimeTable> allStudentsTimeTables = new HashMap<>();

    public UserTimeTable(User user) {
        this.user = user;
        classesAtTimes = new TreeMap<>();
        populateTimeMap(classesAtTimes);
        allStudentsTimeTables.put(user.getUserId(), this);
    }

    /**
     * This fills classesAtTimes map with every possible day and time (there's only 45)
     *
     * @param classesAtTimes TreeMap that holds all times as key and timeslot objects as values in a list
     */
    private void populateTimeMap(TreeMap<String, TimeSlot> classesAtTimes) {
        for (int day = 1; day <= 5; day++) {
            classesAtTimes.put(day + "_09", null);
            for (int time = 10; time <= 17; time++) {
                classesAtTimes.put(day + "_" + time, null);
            }
        }
    }

    public static UserTimeTable getUserTimetable(String userId) {
        return allStudentsTimeTables.get(userId);
    }

    public boolean containsTimeSlot(String dayTime) {
        return classesAtTimes.get(dayTime) != null;
    }

    /**
     *
     */
    public void addTimeSlot(String dayTime, TimeSlot timeSlot) {
        if (containsTimeSlot(dayTime)) {
            throw new IllegalArgumentException("This user already has a module at this time");
        }
        classesAtTimes.put(dayTime, timeSlot);
    }

    public List<TimeSlot> getTimeSlots() {
        return new ArrayList<>(classesAtTimes.values());
    }

    /**
     * Prints Timetable for this user in corrected order, (can and most likely will be replaced with toString())
     */
    public void printTimeTable() {
        for (int day = 1; day <= 5; day++) {
            String dayTime;
            String dayFull = TimeSlot.getDayFromNumber(day);
            System.out.println("######################");
            System.out.println("Day of Week " + dayFull);
            System.out.println("######################");
            for (int time = 9; time <= 17; time++) {
                dayTime = time > 9 ? day + "_" + time : day + "_09";
                String timeFull = TimeSlot.getTimeFromShortened(time);
                TimeSlot timeSlot = classesAtTimes.get(dayTime);
                System.out.println("----------------------");
                System.out.println(timeFull);
                if (timeSlot == null) {
                    System.out.println("Free Period");
                } else {
                    System.out.println(timeSlot);
                }
            }
        }
        System.out.print("----------------------\n\n");
    }
}

