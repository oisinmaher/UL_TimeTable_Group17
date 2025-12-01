package export.dto;

import com.opencsv.bean.CsvBindByName;

public class TimeSlotCsvDto {
    //@CsvBindByName
    //private String timeSlotId;

    @CsvBindByName
    private String moduleCode;

    @CsvBindByName
    private String classType;

    @CsvBindByName
    private String dayTime;

    @CsvBindByName
    private String roomId;

    @CsvBindByName
    private String teacherId;

    @CsvBindByName
    private String studentIds;

    @CsvBindByName
    private String courseCodes;

    public TimeSlotCsvDto() {}

    public TimeSlotCsvDto(String moduleCode, String classType, String dayTime, String roomId, String teacherId, String studentIds, String courseCodes) {
        this.moduleCode = moduleCode;
        this.classType = classType;
        this.dayTime = dayTime;
        this.roomId = roomId;
        this.teacherId = teacherId;
        this.studentIds = studentIds;
        this.courseCodes = courseCodes;
    }

    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getClassType() {
        return classType;
    }
    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getDayTime() {
        return dayTime;
    }
    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentIds() {
        return studentIds;
    }
    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public String getCourseCodes() {
        return courseCodes;
    }
    public void setCourseCodes(String courseCodes) {
        this.courseCodes = courseCodes;
    }

}