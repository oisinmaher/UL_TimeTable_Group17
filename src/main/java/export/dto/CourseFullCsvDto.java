package export.dto;

import com.opencsv.bean.CsvBindByName;

/**
 * CourseFull Data transfer object
 */
public class CourseFullCsvDto implements CsvDtoInterface{

    @CsvBindByName
    private String courseCode;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String yearNumbers;


    /**
     * No arg-constructor required for CsvToBean
     */
    public CourseFullCsvDto() { }

    /**
     * Constructor for DTO
     * @param courseCode
     * @param name
     * @param yearNumbers
     */
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
