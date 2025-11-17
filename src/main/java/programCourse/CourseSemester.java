package programCourse;
import modules.CourseModule;

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

    private final String name;
    private final List<CourseModule> moduleCodes;

    /**
     * Creates a CourseSemester with a name and list of module codes.
     *
     * @param name        the name of the semester (e.g. "Autumn")
     * @param moduleCodes list of CourseModule objects assigned to this semester
     *
     * @throws IllegalArgumentException if name or moduleCodes is null
     */
    public CourseSemester(String name, List<CourseModule> moduleCodes) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (moduleCodes == null) {
            throw new IllegalArgumentException("moduleCodes is null");
        }

        this.name = name;
        this.moduleCodes = new ArrayList<>(moduleCodes);
    }

    /**
     * Returns the name of the semester.
     *
     * @return semester name as a String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a defensive copy of the list of modules assigned to this semester.
     *
     * @return list of CourseModule objects
     */
    public List<CourseModule> getModuleCodes() {
        return new ArrayList<>(moduleCodes);
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

        if (name.isEmpty()) {
            issues.add("Year" + yearNumber + " has a semester with a blank name");
        }
        if (moduleCodes.isEmpty()) {
            issues.add("Year" + yearNumber + " has no modules listed");
        }

        Set<String> seen = new HashSet<>();
        for (CourseModule m : moduleCodes) {
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
    public List<CourseModule> pickModulesForThisSemester() {

        List<CourseModule> selected = new ArrayList<>();

        String sem = name.toLowerCase();

        for (CourseModule module : moduleCodes) {

            String code = module.getModuleCode();
            if (code == null || code.isEmpty()) {
                continue;
            }

            char lastChar = code.charAt(code.length() - 1);

            if (!Character.isDigit(lastChar)) {
                continue;
            }

            // FIXED: the braces in your original code prevented this from compiling
            int lastDigit = Character.getNumericValue(lastChar);  // ✔ Correct position

            if (sem.contains("autumn") || sem.contains("fall")) {
                // Pick odd-ending module codes
                if (lastDigit % 2 == 1) {
                    selected.add(module);
                }
            }
            else if (sem.contains("spring")) {
                // Pick even-ending module codes
                if (lastDigit % 2 == 0) {
                    selected.add(module);
                }
            }
            else if (sem.contains("summer")) {
                // Summer gets all modules (as per your current rule)
                selected.add(module);
            }
            else {
                throw new IllegalArgumentException(
                    "Invalid semester: '" + name + "'. Only Autumn/Fall and Spring are supported."
                );
            }
        }

        return selected;
    }

    /**
     * Converts the semester to a readable string format:
     * e.g. "Autumn: CS1011, MA4001"
     *
     * @return formatted string representation of this semester
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name).append(": ");
        for (int i = 0; i < moduleCodes.size(); i++) {
            sb.append(moduleCodes.get(i));
            if (i < moduleCodes.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
