package export.dto;

import com.opencsv.bean.CsvBindByName;

public class CourseYearCsvDto implements CsvDtoInterface{

    @CsvBindByName
    private String yearId;

    @CsvBindByName
    private String yearNumber;

    @CsvBindByName
    private String courseSemesters;

    public CourseYearCsvDto(){ }

    public CourseYearCsvDto(String yearId, String yearNumber, String courseSemesters){
        this.yearId = yearId;
        this.yearNumber = yearNumber;
        this.courseSemesters =  courseSemesters;
    }

    public String getYearId() {
        return yearId;
    }
    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getYearNumber() {
        return yearNumber;
    }
    public void setYearNumber(String yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getCourseSemesters() {
        return courseSemesters;
    }
    public void setCourseSemesters(String courseSemesters) {
        this.courseSemesters = courseSemesters;
    }
}
