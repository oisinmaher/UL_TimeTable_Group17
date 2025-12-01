package export.mapper;

import adminPanel1.DataManager;
import export.dto.TeacherCsvDto;
import users.Teacher;

public class TeacherMapper {

    public static TeacherCsvDto flatten(Teacher teacher) {
        return new TeacherCsvDto(
                teacher.getUserId(),
                teacher.getName()
        );
    }

    public static Teacher inflate(TeacherCsvDto teacherCsvDto) {
        String id = teacherCsvDto.getTeacherId();

        if(DataManager.teachers.containsKey(id)){
            return DataManager.teachers.get(id);
        }

        Teacher t = new Teacher(id, teacherCsvDto.getTeacherName());
        DataManager.teachers.put(id,t);
        return t;

    }
}