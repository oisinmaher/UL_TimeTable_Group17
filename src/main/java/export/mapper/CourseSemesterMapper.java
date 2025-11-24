package export.mapper;

import export.dto.CourseSemesterCsvDto;
import programCourse.CourseSemester;

public class CourseSemesterMapper implements MapperInterface{

    public static CourseSemesterCsvDto flatten(CourseSemester cs) {
        return new CourseSemesterCsvDto(
                cs.getSemesterId(),
                cs.getSeason()
        );
    }
}
