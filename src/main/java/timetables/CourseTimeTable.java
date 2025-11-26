package timetables;

import programCourse.CourseFull;

import java.util.*;

public class CourseTimeTable {
    CourseFull courseFull;
    String courseCode;
    TreeMap<String, List<TimeSlot>> classesAtTimes;
    public CourseTimeTable(CourseFull courseFull){
        this.courseFull = courseFull;
        this.courseCode = courseFull.getCode();
        classesAtTimes = new TreeMap<>();
        populateTimeMap(classesAtTimes);
    }

    /**
     * This fills classesAtTimes map with every possible day and time (there's only 45)
     * @param classesAtTimes TreeMap that holds all times as key and timeslot objects as values in a list
     */
    private void populateTimeMap(TreeMap<String, List<TimeSlot>> classesAtTimes){
        for(int day = 1; day <= 5; day++){
            classesAtTimes.put(day + "_09", new ArrayList<>());
            for(int time = 10; time <= 17; time++){
                classesAtTimes.put(day + "_" + time, new ArrayList<>());
            }
        }
    }
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
