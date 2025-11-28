package export.dto;

import com.opencsv.bean.CsvBindByName;

public class CourseModuleCsvDto implements CsvDtoInterface{

    @CsvBindByName
    private String code;

    @CsvBindByName
    private String name;

    public CourseModuleCsvDto() {}

    public CourseModuleCsvDto(String code, String name) {
        this.code = code;
        this.name = name;
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
}
