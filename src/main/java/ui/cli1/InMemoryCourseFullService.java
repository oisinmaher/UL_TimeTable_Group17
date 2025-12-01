package ui.cli1;

import adminPanel1.DataManager;
import adminPanel1.WriteData;
import programCourse.*;

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

        DataManager.courseFulls.put(courseCode.toLowerCase(), courseFull);
        WriteData writer = new WriteData();
        writer.writeCourseFulls();
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
