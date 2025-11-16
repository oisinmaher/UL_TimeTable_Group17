package src.main.java.programCourse;

import src.main.java.modules.CourseModule;

import java.util.*;

/**
 * Represents a single academic year within a program (e.g. Year 1, Year 2).
 *
 * A ProgramYear:
 *  - Belongs to a specific ProgramWithModule
 *  - Has a year number (e.g. 1, 2, 3)
 *  - Contains a list of ProgramSemester objects (e.g. Autumn, Spring)
 *
 * It provides behaviours to:
 *  - Retrieve modules by semester
 *  - Retrieve all modules across the year (without duplicates)
 *  - Map semesters to student group IDs
 *  - Validate the structure of the year and its semesters
 */
public final class ProgramYear {

    private final ProgramWithModule programme;
    private final int yearNumber;
    private final List<ProgramSemester> semesters;

    /**
     * Constructs a ProgramYear with a reference to ProgramWithModule,
     * a year number, and a list of semesters.
     *
     * @param programme  ProgramWithModule this year belongs to (can be null)
     * @param yearNumber the numeric identifier for the year (e.g. 1, 2, 3)
     * @param semesters  the list of ProgramSemester objects for this year
     *
     * @throws IllegalArgumentException if semesters is null
     */
    public ProgramYear(ProgramWithModule programme, int yearNumber, List<ProgramSemester> semesters) {
        if (semesters == null) {
            throw new IllegalArgumentException("semesters is null");
        }
        this.programme = programme;
        this.yearNumber = yearNumber;
        this.semesters = new ArrayList<>(semesters);
    }

    /**
     * Returns the parent program this year belongs to.
     *
     * @return the ProgramWithModule instance, or null if not set
     */
    public ProgramWithModule getProgramme() {
        return programme;
    }

    /**
     * Returns the numeric year number (e.g. 1 for first year).
     *
     * @return the year number
     */
    public int getYearNumber() {
        return yearNumber;
    }

    /**
     * Returns a copy of the list of semesters in this year.
     *
     * @return a new List containing the ProgramSemester objects
     */
    public List<ProgramSemester> getSemesters() {
        return new ArrayList<>(semesters);
    }

   /**
     * Returns the list of CourseModule objects for a given semester name in this year.
     * This now uses ProgramSemester.pickModulesForThisSemester(), which applies
     * the odd/even last digit rule based on the semester name.
     */
    public List<CourseModule> modulesForSemester(String semesterName) {
        if (semesterName == null) {
            return new ArrayList<>();
        }
        String target = semesterName.toLowerCase();
        for (ProgramSemester s : semesters) {
            if (s.getName().toLowerCase().equals(target)) {
                return s.pickModulesForThisSemester();
            }
        }
        return new ArrayList<>();
    }
    /**
     * Returns all CourseModule objects across all semesters in this year,
     * with duplicates removed. If a module appears in more than one semester,
     * it will only be included once in the returned list.
     *
     * @return a List of unique CourseModule objects for this year
     */
    public List<CourseModule> allModules() {
        List<CourseModule> out = new ArrayList<>();
        Set<CourseModule> seen = new HashSet<>();
        for (ProgramSemester s : semesters) {
            for (CourseModule m : s.getModuleCodes()) {
                if (!seen.contains(m)) {
                    seen.add(m);
                    out.add(m);
                }
            }
        }
        return out;
    }

    /**
     * Maps semester names to lists of student group IDs.
     *
     * The input map should contain:
     *  - key: semester name (e.g. "Autumn")
     *  - value: list of student group IDs (e.g. ["G1A", "G1B"])
     *
     * This method:
     *  - Validates that the provided semester names exist in this ProgramYear
     *  - Returns a copy of the mapping
     *
     * @param semesterToGroupIDs a map from semester name to list of student group IDs
     * @return a new Map with the same keys and copies of the group lists
     *
     * @throws IllegalArgumentException if semesterToGroupIDs is null
     *                                  or contains a semester name that doesn't exist in this year
     */
    public Map<String, List<String>> mapToStudentGroups(Map<String, List<String>> semesterToGroupIDs) {
        if (semesterToGroupIDs == null) {
            throw new IllegalArgumentException("SemesterToGroupIDs can't be null");
        }
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<String, List<String>> e : semesterToGroupIDs.entrySet()) {
            String semKey = e.getKey();
            if (!hasSemester(semKey)) {
                throw new IllegalArgumentException("Unknown semester for year " + yearNumber + ": " + semKey);
            }
            List<String> groups = new ArrayList<>(e.getValue());
            result.put(semKey, groups);
        }
        return result;
    }

    /**
     * Checks whether this ProgramYear contains a semester with the given name.
     * The comparison is case-insensitive.
     *
     * @param name the semester name to search for
     * @return true if a semester with the given name exists, false otherwise
     */
    public boolean hasSemester(String name) {
        if (name == null) {
            return false;
        }
        String target = name.toLowerCase();
        for (ProgramSemester s : semesters) {
            if (s.getName().toLowerCase().equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates the structure of this ProgramYear and its semesters.
     *
     * The following checks are performed:
     *  - The year must have at least one semester
     *  - Each semester must have a non-blank name
     *  - Semester names must be unique (case-insensitive)
     *  - Each ProgramSemester is validated via its own validate method
     *
     * Any issues found are returned as a list of error messages. An empty list
     * means the year is structurally valid.
     *
     * @return a List of validation issue descriptions, or an empty list if valid
     */
    private List<String> validate() {
        List<String> issues = new ArrayList<>();
        if (semesters.isEmpty()) {
            issues.add("Year " + yearNumber + " has no semesters.");
            return issues;
        }

        Set<String> seenNames = new HashSet<>();
        for (ProgramSemester s : semesters) {
            String key;
            if (s.getName() == null) {
                key = "";
            } else {
                key = s.getName().toLowerCase();
            }

            if (key.isEmpty()) {
                issues.add("Year" + yearNumber + "has a semester with a blank name");
            } else if (seenNames.contains(key)) {
                issues.add("Year " + yearNumber + " has duplicate semester: " + s.getName());
            } else {
                seenNames.add(key);
            }
            issues.addAll(s.validate(yearNumber));
        }
        return issues;
    }
}
