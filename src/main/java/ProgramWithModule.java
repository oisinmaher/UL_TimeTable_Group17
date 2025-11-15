package src.main.java.timetables;

import src.main.java.modules.CourseModule;
import java.util.*;

public class ProgramWithModule {

    private String code;
    private String name;
    private List<ProgramYear> years; // CHANGED: was List<ProgramWithCourseModule.ProgramYear>

    // Constructor for ProgramWithCourseModule.
    // Validates that code, name, and years are not null, then copies the list of years.
    public ProgramWithModule(String code, String name, List<ProgramYear> years) { // CHANGED: parameter type
        if (code == null) {
            throw new IllegalArgumentException("Code can't be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        if (years == null) {
            throw new IllegalArgumentException("Years can't be null");
        }
        this.code = code;
        this.name = name;
        this.years = new ArrayList<>(years);
    }

    // We can add getters or other behaviour here later if needed.
}

