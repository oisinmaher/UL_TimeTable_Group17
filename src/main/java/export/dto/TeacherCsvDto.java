package export.dto;

import com.opencsv.bean.CsvBindByName;

public class TeacherCsvDto {

    @CsvBindByName
    private String teacherId;

    @CsvBindByName
    private String teacherName;

    public TeacherCsvDto() {}

    public TeacherCsvDto(String teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}