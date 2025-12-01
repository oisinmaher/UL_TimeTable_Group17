package export.dto;

import com.opencsv.bean.CsvBindByName;

public class GroupCsvDto {

    @CsvBindByName
    private String moduleCode;

    @CsvBindByName
    private String courseCode;

    @CsvBindByName
    private String teacher;

    @CsvBindByName
    private String rooms;

    public GroupCsvDto() {}

    public GroupCsvDto(String moduleCode, String courseCode, String teacher, String rooms) {
        this.moduleCode = moduleCode;
        this.courseCode = courseCode;
        this.teacher = teacher;
        this.rooms = rooms;
    }

    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRooms() {
        return rooms;
    }
    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

}
