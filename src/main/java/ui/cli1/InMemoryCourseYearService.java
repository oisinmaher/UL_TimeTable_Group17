package ui.cli1;

import programCourse.CourseFull;

public class InMemoryCourseYearService implements CourseYearService{

    @Override
    public boolean addYear(String courseCode, String year) {
        if(!CourseFull.containsCode(courseCode)){
            System.out.println("This course code doesnt exist");
            return false;
        }
        CourseFull courseFull = CourseFull.getCourseFromCode(courseCode);
        if(courseFull.containsYear(year)){
            System.out.println("This course already has this year");
            return false;
        }
        courseFull.addCourseYear(year);
        return true;
    }
}
