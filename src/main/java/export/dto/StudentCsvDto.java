package export.dto;

import com.opencsv.bean.CsvBindByName;

/**
 * Data Transfer Object for exporting Student objects to CSV.
 * Only contains flat fields needed for export - not objects.
 */
public class StudentCsvDto implements CsvDtoInterface{

    //This is the header on the CSV file
    @CsvBindByName(column = "student_id")
    private String studentId;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "course_code")
    private String courseCode;

    @CsvBindByName(column = "year")
    private String year;

    /**
     * No-arg constructor required for writer
     */
    public StudentCsvDto() {}

    /**
     * Takes in all domain fields to make the DTO
     * @param studentId the student id
     * @param name the student name
     * @param courseCode the student course code
     * @param year the year a student is in
     */
    public StudentCsvDto(String studentId, String name, String courseCode, String year) {
        this.studentId = studentId;
        this.name = name;
        this.courseCode = courseCode;
        this.year = year;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}
