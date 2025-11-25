package export.mapper;

import export.dto.CourseModuleCsvDto;
import modules.CourseModule;

public class CourseModuleMapper implements MapperInterface{

    public static CourseModuleCsvDto flatten(CourseModule cm){
        return new CourseModuleCsvDto(
                cm.getModuleCode(),
                cm.getModuleName()
        );
    }

}
