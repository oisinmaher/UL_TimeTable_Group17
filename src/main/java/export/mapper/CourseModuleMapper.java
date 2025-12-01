package export.mapper;

import export.dto.CourseModuleCsvDto;
import modules.CourseModule;
import programCourse.CourseFull;

public class CourseModuleMapper {

    public static CourseModuleCsvDto flatten(CourseModule cm){
        return new CourseModuleCsvDto(
                cm.getModuleCode(),
                cm.getModuleName(),
                cm.getLecHours(),
                cm.getLabHours(),
                cm.getTutHours()
        );
    }

    public static CourseModule inflate(CourseFull course, CourseModuleCsvDto cm){
        CourseModule c = new CourseModule(cm.getName(),cm.getCode(), course);

        c.setLecHours(cm.getLecHours());
        c.setLabHours(cm.getLabHours());
        c.setTutHours(cm.getTutHours());

        return c;
    }

}
