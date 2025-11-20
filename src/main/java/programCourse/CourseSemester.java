package src.main.java.programCourse;

import src.main.java.modules.CourseModule;

import java.util.*;

/**
 * Represents a semester within a courseYear (e.g. Autumn, Spring).
 * 
 * Each semester:
 *  - Has a name (Autumn, Spring, Summer...)
 *  - Contains a list of CourseModule objects
 *  - Can validate its modules
 *  - Can select a subset of modules based on the semester rules
 */
public final class CourseSemester {

    private final String season;
    // module code -> Module object.  "cs4404" -> CourseModule Object
    private final Map<String, CourseModule> assignedModules;


    /**
     * Creates a CourseSemester with a name and list of module codes.
     *
     * @param season the name of the semester (e.g. "Autumn")
     *
     * @throws IllegalArgumentException if name or assignedModules is null
     */
    public CourseSemester(String season) {
        if (season == null) {
            throw new IllegalArgumentException("name is null");
        }
        this.season = season;
        this.assignedModules = new HashMap<>();
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
     *
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

    /**
     * Selects a subset of modules for this semester based on the UL rules:
     *
     *  - Autumn/Fall → modules ending in an ODD digit (1,3,5,7,9)
     *  - Spring      → modules ending in an EVEN digit (0,2,4,6,8)
     *  - Summer      → currently returns ALL modules
     *
     * If the semester name does not match Autumn/Fall, Spring, or Summer,
     * an exception is thrown.
     *
     * @return list of CourseModule objects that match the semester's filtering rules
     *
     * @throws IllegalArgumentException if the semester name is not recognised
     */
   
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
