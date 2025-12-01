package export.mapper;

import export.dto.CourseSemesterCsvDto;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;

import java.util.List;

public class CourseSemesterMapper {

    public static CourseSemesterCsvDto flatten(CourseSemester cs) {
        List<String> codes = cs.getModuleCodes();

        String mc = String.join(",", codes);

        return new CourseSemesterCsvDto(
                cs.getSemesterId(),
                cs.getSeason(),
                mc
        );
    }


    public static CourseSemester inflate(CourseYear y, CourseFull c, CourseSemesterCsvDto s){

        CourseSemester cs = new CourseSemester(y, s.getSeason(),c);
        for (String code:s.getModuleCodes().split(",")){
            cs.addExistingModule(code);
        }

        return cs;
    }
}
