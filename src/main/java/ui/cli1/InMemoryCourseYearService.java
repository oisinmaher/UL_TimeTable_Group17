package ui.cli1;

import adminPanel1.DataManager;
import adminPanel1.WriteData;
import programCourse.CourseFull;
import programCourse.CourseYear;

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
        CourseYear newYear = courseFull.addCourseYear(year);
        DataManager.courseYears.put(newYear.getYearId(), newYear);
        WriteData writer = new WriteData();
        writer.writeCourseYears();
        return true;
    }

}
