package ui.cli1;

import programCourse.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCourseFullService implements CourseFullService {

    /**
     * Creates a university course and return true or false depending on this
     * @param courseCode code of a course
     * @param courseName name of a course
     * @return true if the course was successfully made, false otherwise
     */
    @Override
    public boolean createCourse(String courseCode, String courseName) {
        if(CourseFull.containsCode(courseCode)){
            System.out.println("Course Code already exists");
            return false;
        }
        CourseFull courseFull = new CourseFull(courseCode, courseName);
        return true;
    }

    /**
     * Gets name of a course from a given course code
     * @param courseCode code of a course
     * @return the name of a university course
     */
    @Override
    public String getNameFromCode(String courseCode) {
        return CourseFull.getCourseFromCode(courseCode.toLowerCase()).getName();
    }

    /**
     * Gets the list of all course codes and course names
     * @return list of all course codes and course names
     */
    @Override
    public List<String> listAllCourses() {
        return CourseFull.getAllCourses();
    }

    /**
     * Gets the list of years of this CourseFull object
     * @param courseCode code of a course
     * @return list of years of this CourseFull object as a string
     */
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
