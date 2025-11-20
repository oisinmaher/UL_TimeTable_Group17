package ui.cli1;

import programCourse.CourseFull;
import programCourse.CourseYear;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCourseFullService implements CourseFullService {

    @Override
    public boolean createCourse(String courseCode, String courseName) {
        if(CourseFull.containsCode(courseCode)){
            System.out.println("Course Code already exists");
            return false;
        }
        CourseFull courseFull = new CourseFull(courseCode, courseName);
        return true;
    }

    @Override
    public String getNameFromCode(String courseCode) {
        return CourseFull.getCourseFromCode(courseCode.toLowerCase()).getName();
    }

    @Override
    public List<String> listAllCourses() {
        return CourseFull.getAllCourses();
    }

    @Override
    public List<String> listCourseYears(String courseCode) {
        List<CourseYear> courseYears =  CourseFull.getCourseFromCode(courseCode).getCourseYears();
        List<String> yearsList = new ArrayList<>();
        for(CourseYear courseYear : courseYears){
            yearsList.add(courseYear.getYearNumber());
        }
        return yearsList;
    }

}
