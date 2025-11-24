package timetables;

import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuildCourseTimetable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course code");
        String courseCode = sc.nextLine().toLowerCase();
        if(!CourseFull.containsCode(courseCode)){
            throw new IllegalArgumentException("This module code doesnt exist");
        }
        System.out.println("Enter year of your course");
        String yearOfStudy = sc.nextLine().toLowerCase();
        if(!CourseYear.containsCourseYearId(courseCode + "_" + yearOfStudy)){
            throw new IllegalArgumentException("This module doesnt have this year");
        }
        System.out.println("Enter semester name, autumn / spring");
        String semesterName = sc.nextLine().toLowerCase();
        String courseSemesterId = courseCode + "_" + yearOfStudy + "_" + semesterName;
        CourseSemester courseSemester = CourseSemester.getSemesterById(courseSemesterId);
        List<CourseModule> courseModuleList = courseSemester.getModuleObjects();
        List<String> allTimes = new ArrayList<>();
        for(CourseModule courseModule : courseModuleList){
            Group group = courseModule.getGroupAssigned();
            for(TimeSlot timeSlot : group.getTimeSlots("lecture")){
                allTimes.add("Lecture at: " + timeSlot.toString());
            }
            for(TimeSlot timeSlot : group.getTimeSlots("lab")){
                allTimes.add("Lab at: " + timeSlot.toString());
            }
            for(TimeSlot timeSlot : group.getTimeSlots("tutorial")){
                allTimes.add("Tutorial at: " + timeSlot.toString());
            }
        }
        System.out.println(allTimes);
    }
}
