package timetables;

import programCourse.CourseFull;
import users.Student;
import users.User;

import java.util.*;

public class UserTimeTable {
    String courseCode;
    User user;
    Map<String, TimeSlot> classesAtTimes;
    final private static Map<String, UserTimeTable> allStudentsTimeTables = new HashMap<>();


    public UserTimeTable(User user) {
        this.user = user;
        classesAtTimes = new HashMap<>();
        populateTimeMap(classesAtTimes);
        allStudentsTimeTables.put(user.getUserId(), this);
    }

    /**
     * This fills classesAtTimes map with every possible day and time (there's only 45)
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

}

