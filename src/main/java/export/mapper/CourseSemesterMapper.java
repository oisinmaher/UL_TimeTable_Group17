package export.mapper;

import export.dto.CourseSemesterCsvDto;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;

import java.util.List;

public class CourseSemesterMapper {

    /**
     * Flattens the CourseSemester object into data
     * @param cs CourseSemester
     * @return CourseSemesterCSV data transfer object
     */
    public static CourseSemesterCsvDto flatten(CourseSemester cs) {
        List<String> codes = cs.getModuleCodes();

        String mc = String.join(",", codes);

        return new CourseSemesterCsvDto(
                cs.getSemesterId(),
                cs.getSeason(),
                mc
        );
    }

    /**
     * Inflates the data back to a CourseSemester objects
     * @param y CourseYear
     * @param c CourseFull
     * @param s CourseSemesterCsvDTO
     * @return CourseSemester
     */
    public static CourseSemester inflate(CourseYear y, CourseFull c, CourseSemesterCsvDto s){

        CourseSemester cs = new CourseSemester(y, s.getSeason(),c);
        for (String code:s.getModuleCodes().split(",")){
            cs.addExistingModule(code);
        }

        return cs;
    }
}