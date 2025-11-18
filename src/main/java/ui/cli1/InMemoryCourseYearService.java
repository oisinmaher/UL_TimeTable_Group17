package src.main.java.ui.cli1;

import src.main.java.programCourse.CourseFull;
import src.main.java.programCourse.CourseYear;

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
        CourseYear courseYear = new CourseYear(courseFull, year);
        return true;
    }
}
