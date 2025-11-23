package export.dto;

import com.opencsv.bean.CsvBindByName;

import java.util.List;

public class CourseFullCsvDto implements CsvDtoInterface{

    @CsvBindByName
    private String courseCode;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String yearNumbers;


    public CourseFullCsvDto() { }

    public CourseFullCsvDto(String courseCode, String name, String yearNumbers) {
        this.courseCode = courseCode;
        this.name = name;
        this.yearNumbers = yearNumbers;
    }

    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getYearNumbers() {
        return yearNumbers;
    }
    public void setYearNumbers(String yearNumbers) {
        this.yearNumbers = yearNumbers;
    }

}
