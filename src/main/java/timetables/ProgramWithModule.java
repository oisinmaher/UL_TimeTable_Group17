package src.main.java.timetables;

import src.main.java.modules.CourseModule;
import java.util.*;

/**
 * Represents an entire academic program (degree) such as
 * BSc Computer Science or BA Business.
 *
 * A ProgramWithModule contains:
 *  - A program code (e.g., "BSC-CS")
 *  - A human-readable program name
 *  - A list of ProgramYear objects that describe the structure of the degree
 *
 * This class is the high level class for the program structure
 */
public class ProgramWithModule {

    private String code;
    private String name;
    private List<ProgramYear> years;

    /**
     * Constructs a ProgramWithModule object by assigning the program code,
     * name, and list of academic years that make up the program.
     *
     * @param code  the program identifier (e.g. "BSC-CS")
     * @param name  the program name displayed to users
     * @param years the list of ProgramYear objects representing each academic year
     *
     * @throws IllegalArgumentException if any argument is null
     */
    public ProgramWithModule(String code, String name, List<ProgramYear> years) {

        // Validate inputs
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


}
