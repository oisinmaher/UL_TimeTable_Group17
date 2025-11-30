package export.mapper;

import export.dto.TeacherCsvDto;
import users.Teacher;

public class TeacherMapper implements MapperInterface{

    public static TeacherCsvDto flatten(Teacher teacher) {
        return new TeacherCsvDto(
                teacher.getUserId(),
                teacher.getName()
        );
    }

    public static Teacher inflate(TeacherCsvDto teacherCsvDto) {
        return new Teacher(
                teacherCsvDto.getTeacherId(),
                teacherCsvDto.getTeacherName()
        );
    }
}
