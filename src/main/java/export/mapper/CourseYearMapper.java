package export.mapper;

import export.dto.CourseFullCsvDto;
import export.dto.CourseYearCsvDto;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;

import java.util.ArrayList;
import java.util.List;

public class CourseYearMapper implements MapperInterface {


    public static CourseYearCsvDto flatten(CourseYear y){
        List<String> semesters = new ArrayList<>();
        for(CourseSemester s : y.getSemesters()){
            semesters.add(s.toString());
        }

        String years = String.join(",", semesters);

        return new CourseYearCsvDto(
                y.getYearId(),
                y.getYearNumber(),
                years
        );
    }

}
