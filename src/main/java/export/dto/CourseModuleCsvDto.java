package export.dto;

import com.opencsv.bean.CsvBindByName;

public class CourseModuleCsvDto {

    @CsvBindByName
    private String code;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private int LecHours;

    @CsvBindByName
    private int LabHours;

    @CsvBindByName
    private int TutHours;

    public CourseModuleCsvDto() {}

    /**
     * Constructor for DTO
     * @param code module code
     * @param name name of a module
     * @param LecHours number of lecture hours
     * @param LabHours number of lab hours
     * @param TutHours number of tutorial hours
     */
    public CourseModuleCsvDto(String code, String name, int LecHours, int LabHours, int TutHours) {
        this.code = code;
        this.name = name;
        this.LecHours = LecHours;
        this.LabHours = LabHours;
        this.TutHours = TutHours;
    }

    /**
     * Gets the module code
     * @return code of a module
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the module code
     * @param code code of a module
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the module name
     * @return name of a module
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the module name
     * @param name name of a module
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the number of lecture hours
     * @return lecture hours
     */
    public int getLecHours() {
        return LecHours;
    }

    /**
     * Sets the number of lecture hours
     * @param lecHours lecture hours
     */
    public void setLecHours(int lecHours) {
        LecHours = lecHours;
    }

    /**
     * Gets the number of lab hours
     * @return lab hours
     */
    public int getLabHours() {
        return LabHours;
    }

    /**
     * Sets the number of lab hours
     * @param labHours lab hours
     */
    public void setLabHours(int labHours) {
        LabHours = labHours;
    }

    /**
     * Gets the number of tutorial hours
     * @return tutorial hours
     */
    public int getTutHours() {
        return TutHours;
    }

    /**
     * Sets the number of tutorial hours
     * @param tutHours tutorial hours
     */
    public void setTutHours(int tutHours) {
        TutHours = tutHours;
    }
}
