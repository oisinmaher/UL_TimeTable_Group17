package ui.cli1;

import adminPanel1.DataManager;
import adminPanel1.WriteData;
import programCourse.CourseFull;
import programCourse.CourseYear;

public class InMemoryCourseYearService implements CourseYearService{

    /**
     * Adds a year to a courseFull object
     * @param courseCode code of a course
     * @param year year of a course
     * @return true if the year was added successfully to the course, false if either
     *         the given course code does not exist or if the given year has already
     *         been added to this course
     */
    @Override
    public boolean addYear(String courseCode, String year) {
        if(!CourseFull.containsCode(courseCode)){
            System.out.println("This course code doesn't exist");
            return false;
        }
        CourseFull courseFull = DataManager.courseFulls.get(courseCode);
        if(courseFull.containsYear(year)){
            System.out.println("This course already has this year");
            return false;
        }
        CourseYear newYear = courseFull.addCourseYear(year);
        DataManager.courseYears.put(newYear.getYearId(), newYear);
        DataManager.courseFulls.put(courseCode, courseFull);
        WriteData writer = new WriteData();
        writer.writeCourseYears();
        return true;
    }

}
