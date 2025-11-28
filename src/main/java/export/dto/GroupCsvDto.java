package export.dto;

import com.opencsv.bean.CsvBindByName;

public class GroupCsvDto implements CsvDtoInterface{

    @CsvBindByName
    private String moduleCode;

    @CsvBindByName
    private String courseCode;

    @CsvBindByName
    private String studentsInGroup;

    @CsvBindByName
    private String teacher;

    @CsvBindByName
    private String rooms;

    @CsvBindByName
    private String usedTimes;

    public GroupCsvDto() {}

    public GroupCsvDto(String moduleCode, String courseCode, String studentsInGroup, String teacher, String rooms, String usedTimes) {
        this.moduleCode = moduleCode;
        this.courseCode = courseCode;
        this.studentsInGroup = studentsInGroup;
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

    public String getStudentsInGroup() {
        return studentsInGroup;
    }
    public void setStudentsInGroup(String studentsInGroup) {
        this.studentsInGroup = studentsInGroup;
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

    public String getUsedTimes() {
        return usedTimes;
    }
    public void setUsedTimes(String usedTimes) {
        this.usedTimes = usedTimes;
    }
}
