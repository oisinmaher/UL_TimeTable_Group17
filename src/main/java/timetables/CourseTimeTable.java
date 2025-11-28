package timetables;

import programCourse.CourseFull;

import java.util.*;

/**
 * CourseTimeTable class.
 * Contains:
 *      - Map of dayTime code to a list of TimeSlot objects
 */
public class CourseTimeTable {
    CourseFull courseFull;
    String courseCode;
    Map<String, List<TimeSlot>> classesAtTimes;

    /**
     * Constructor that creates a map of all times in the form "2_14" to timeslot objects, and then
     * adds every possible time in the working week to this map ("1_09" to "5_17")
     * @param courseFull a university course
     */
    public CourseTimeTable(CourseFull courseFull){
        this.courseFull = courseFull;
        this.courseCode = courseFull.getCode();
        classesAtTimes = new HashMap<>();
        populateTimeMap(classesAtTimes);
    }

    /**
     * This fills classesAtTimes map with every possible day and time (there's only 45)
     * This method is duplicated in UserTimeTable
     * @param classesAtTimes TreeMap that holds all times as key and timeslot objects as values in a list
     */
    private void populateTimeMap(Map<String, List<TimeSlot>> classesAtTimes){
        for(int day = 1; day <= 5; day++){
            classesAtTimes.put(day + "_09", new ArrayList<>());
            for(int time = 10; time <= 17; time++){
                classesAtTimes.put(day + "_" + time, new ArrayList<>());
            }
        }
    }

    /**
     * Adds a scheduled class to the corresponding time on the timetable
     * @param dayTime a code that represents the day of the week and the time on the hour together
     * @param timeSlot a space on the timetable that is filled in
     */
    public void addTimeSlot(String dayTime, TimeSlot timeSlot){
        classesAtTimes.get(dayTime).add(timeSlot);
    }

    /**
     * Prints Timetable for this course in corrected order, (can and most likely will be replaced with toString())
     */
    public void printTimeTable(){
        for(int day = 1; day <= 5; day++){
            String dayTime;
            String dayFull = TimeSlot.getDayFromNumber(day);
            System.out.println("######################");
            System.out.println("Day of Week " + dayFull);
            System.out.println("######################");
            for(int time = 9; time <= 17; time++){
                dayTime = time > 9 ? day + "_" + time : day + "_09";
                String timeFull = TimeSlot.getTimeFromShortened(time);
                List<TimeSlot> timeSlots = classesAtTimes.get(dayTime);
                System.out.println("----------------------");
                System.out.println(timeFull);
                if(timeSlots.isEmpty()){
                    System.out.println("Free Period");
                }
                else{
                    for(TimeSlot timeSlot : timeSlots){
                        System.out.println(timeSlots.toString());
                    }
                }
            }
            System.out.print("----------------------\n\n");
        }
    }
}
