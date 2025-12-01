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
     * @param courseCode code of a course
     * @param name name of a course
     * @param yearNumbers number of years of course
     */
    public CourseFullCsvDto(String courseCode, String name, String yearNumbers) {
        this.courseCode = courseCode;
        this.name = name;
        this.yearNumbers = yearNumbers;
    }

    /**
     * Gets the course code
     * @return code of a course
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the code of a course
     * @param courseCode code of a course
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Gets the name of a course
     * @return name of a course
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of a course
     * @param name name of a course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the number of years of this course
     * @return number of years
     */
    public String getYearNumbers() {
        return yearNumbers;
    }

    /**
     * Gets the number of years of this course
     * @param yearNumbers number of years
     */
    public void setYearNumbers(String yearNumbers) {
        this.yearNumbers = yearNumbers;
    }

}
