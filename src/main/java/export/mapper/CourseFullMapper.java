package export.mapper;

import export.dto.CourseFullCsvDto;
import programCourse.CourseFull;
import programCourse.CourseYear;

import java.util.ArrayList;
import java.util.List;

public class CourseFullMapper {

    public static CourseFullCsvDto flatten(CourseFull c){
        List<String> yearNumbers = new ArrayList<>();
        for(CourseYear year : c.getCourseYears()){
            yearNumbers.add(year.getYearNumber());
        }

        String years = String.join(",", yearNumbers);

        return new CourseFullCsvDto(
                c.getCode(),
                c.getName(),
                years
        );
    }


    public static CourseFull inflate(CourseFullCsvDto c){

        CourseFull course = new CourseFull(c.getCourseCode(), c.getName());

        String[] years = c.getYearNumbers().split(",");

        for(String year : years){
            course.addCourseYear(year.trim());
        }

        return course;
    }

}