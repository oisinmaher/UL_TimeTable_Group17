package export.mapper;

import export.dto.CourseYearCsvDto;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;

import java.util.ArrayList;
import java.util.List;

public class CourseYearMapper {


    public static CourseYearCsvDto flatten(CourseYear y){
        List<String> semesters = new ArrayList<>();
        for(CourseSemester s : y.getSemesters()){
            semesters.add(s.getSeason());
        }

        String semester = String.join(",", semesters);

        return new CourseYearCsvDto(
                y.getYearId(),
                y.getYearNumber(),
                semester
        );
    }


    public static CourseYear inflate(CourseFull c, CourseYearCsvDto y){
        String yearNum = y.getYearNumber();

        CourseYear year = c.getCourseYear(yearNum);

        String[] semesters = y.getCourseSemesters().split(",");

        for(String s : semesters){
            year.addSemester(s);
        }

        return year;
    }
}