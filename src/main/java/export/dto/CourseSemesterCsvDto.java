package export.dto;

import com.opencsv.bean.CsvBindByName;

public class CourseSemesterCsvDto implements CsvDtoInterface{

    @CsvBindByName
    private String semesterId;

    @CsvBindByName
    private String season;

    @CsvBindByName
    private String moduleCodes;

    public CourseSemesterCsvDto() { }

    public CourseSemesterCsvDto(String semesterId, String season, String moduleCodes) {
        this.semesterId = semesterId;
        this.season = season;
        this.moduleCodes = moduleCodes;
    }

    public String getSemesterId() {
        return semesterId;
    }
    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }

    public String getModuleCodes() {
        return moduleCodes;
    }
    public void setModuleCodes(String moduleCodes) {
        this.moduleCodes = moduleCodes;
    }
}
