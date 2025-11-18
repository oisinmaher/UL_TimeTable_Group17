package src.main.java.programCourse;

import src.main.java.modules.CourseModule;
import src.main.java.programCourse.CourseFull;
import src.main.java.programCourse.CourseYear;
import java.util.*;

/**
 * Represents a semester within a courseYear (e.g. Autumn, Spring).
 * 
 * Each semester:
 *  - Has a name (Autumn, Spring, Summer...)
 *  - Contains a list of CourseModule objects
 *  - Can validate its modules
 *  - Can select a subset of modules based on the semester rules
 *  - Is NOT mutually referred to the CourseYear
 */
public final class CourseSemester {
    // Semester id = CourseYearId + "_" + season
    private String semesterId;
    private final String season;
    // module code -> Module object.  "cs4404" -> CourseModule Object
    private final Map<String, CourseModule> assignedModules;

    /**
     * Creates a CourseSemester with a name and list of module codes.
     * @param season the name of the semester (e.g. "Autumn")
     * @throws IllegalArgumentException if name is null
     *
     * NOTE, as of Right now no mutual reference to the CourseYear its assigned
     */
    public CourseSemester(CourseYear courseYear, String season) {
        if (season == null) {
            throw new IllegalArgumentException("season is null");
        }
        season = season.toLowerCase();
        if (season.equals("spring") ^ season.equals("autumn")) {
            throw new IllegalArgumentException("Season must be spring or autumn");
        }
        this.semesterId = courseYear.getYearId() + "_" + season;
        this.season = season;
        this.assignedModules = new HashMap<>();
    }
    public void addExistingModule(String moduleName){

    }

    /**
     * Returns the name of the semester.
     *
     * @return semester name as a String
     */
    public String getSeason() {
        return this.season;
    }

    /**
     * Returns a defensive copy of the list of modules assigned to this semester.
     *
     * @return list of CourseModule objects
     */
    public List<String> getModuleCodes() {
        return new ArrayList<>(assignedModules.keySet());
    }


    /**
     * Validates the semester and its modules.
     *
     * Checks:
     *  - Semester name is not blank
     *  - Semester has at least one module
     *  - All module codes are non-null and non-blank
     *  - No duplicate module codes in this semester
     * @param yearNumber the academic year number this semester belongs to
     * @return list of validation error messages, empty if no issues
     */
    public List<String> validate(int yearNumber) {
        List<String> issues = new ArrayList<>();

        if (season.isEmpty()) {
            issues.add("Year" + yearNumber + " has a semester with a blank name");
        }
        if (assignedModules.isEmpty()) {
            issues.add("Year" + yearNumber + " has no modules listed");
        }

        Set<String> seen = new HashSet<>();
        for (CourseModule m : assignedModules.values()) {
            if (m == null || m.getModuleCode().isEmpty()) {
                issues.add("Year" + yearNumber + " has a blank module code");
            } else if (seen.contains(m.getModuleCode())) {
                issues.add("Year" + yearNumber + " has a module that's already listed");
            } else {
                seen.add(m.getModuleCode());
            }
        }
        return issues;
    }


    @Override
    public boolean equals(Object o) { // 👈 FIX 1: Must take Object
        if (this == o) return true;

        // Check for null and class type
        if (o == null || getClass() != o.getClass()) return false;

        // Casts the object to TimeSlot
        CourseSemester that = (CourseSemester) o;

        return this.semesterId.equals(that.semesterId);
    }
    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }
    /**
     * Converts the semester to a readable string format:
     * e.g. "Autumn: CS1011, MA4001"
     *
     * @return formatted string representation of this semester
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(season).append(": ");
        for (int i = 0; i < assignedModules.size(); i++) {
            sb.append(assignedModules.get(i));
            if (i < assignedModules.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
