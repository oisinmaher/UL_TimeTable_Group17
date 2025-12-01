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

    public CourseModuleCsvDto(String code, String name, int LecHours, int LabHours, int TutHours) {
        this.code = code;
        this.name = name;
        this.LecHours = LecHours;
        this.LabHours = LabHours;
        this.TutHours = TutHours;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getLecHours() {
        return LecHours;
    }
    public void setLecHours(int lecHours) {
        LecHours = lecHours;
    }

    public int getLabHours() {
        return LabHours;
    }
    public void setLabHours(int labHours) {
        LabHours = labHours;
    }

    public int getTutHours() {
        return TutHours;
    }
    public void setTutHours(int tutHours) {
        TutHours = tutHours;
    }
}
