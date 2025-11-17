package src.main.java.programCourse;

import src.main.java.modules.CourseModule;
import src.main.java.programCourse.CourseSemester;
import src.main.java.programCourse.CourseFull;

import java.util.*;

/**
 * Represents a single academic year within a course (e.g. Year 1, Year 2).
 *
 * A CourseYear:
 *  - Belongs to a specific CourseFull
 *  - Has a year number (e.g. 1, 2, 3)
 *  - Contains a list of CourseSemesters objects (e.g. Autumn, Spring)
 *
 * It provides behaviours to:
 *  - Retrieve modules by semester
 *  - Retrieve all modules across the year (without duplicates)
 *  - Map semesters to student group IDs
 *  - Validate the structure of the year and its semesters
 */
public final class CourseYear{

    private final CourseFull courseFull;
    private final int yearNumber;
    private final List<CourseSemester> courseSemesters;

    /**
     * Constructs a CourseYear with a reference to CourseWithModule,
     * a year number, and a list of semesters.
     *
     * @param courseFull  CourseWithModule this year belongs to (can be null)
     * @param yearNumber the numeric identifier for the year (e.g. 1, 2, 3)
     * @param courseSemesters  the list of CourseSemester objects for this year
     *
     * @throws IllegalArgumentException if semesters is null
     */
    public CourseYear(CourseFull courseFull, int yearNumber, List<CourseSemester> courseSemesters) {
        if (courseSemesters == null) {
            throw new IllegalArgumentException("semesters is null");
        }
        this.courseFull = courseFull;
        this.yearNumber = yearNumber;
        this.courseSemesters = new ArrayList<>(courseSemesters);
    }

    /**
     * Returns the parent course this year belongs to.
     *
     * @return the CourseFull instance, or null if not set
     */
    public CourseFull getCourseme() {
        return courseFull;
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
     * @return a new List containing the CourseSemester objects
     */
    public List<CourseSemester> getSemesters() {
        return new ArrayList<>(courseSemesters);
    }

   /**
     * Returns the list of CourseModule objects for a given semester name in this year.
     * This now uses CourseSemester.pickModulesForThisSemester(), which applies
     * the odd/even last digit rule based on the semester name.
     */
    public List<CourseModule> modulesForSemester(String semesterName) {
        if (semesterName == null) {
            return new ArrayList<>();
        }
        String target = semesterName.toLowerCase();
        for (CourseSemester s : courseSemesters) {
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
        for (CourseSemester s : courseSemesters) {
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
     *  - Validates that the provided semester names exist in this CourseYear
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
     * Checks whether this CourseYear contains a semester with the given name.
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
        for (CourseSemester s : courseSemesters) {
            if (s.getName().toLowerCase().equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates the structure of this CourseYear and its semesters.
     *
     * The following checks are performed:
     *  - The year must have at least one semester
     *  - Each semester must have a non-blank name
     *  - Semester names must be unique (case-insensitive)
     *  - Each CourseSemester is validated via its own validate method
     *
     * Any issues found are returned as a list of error messages. An empty list
     * means the year is structurally valid.
     *
     * @return a List of validation issue descriptions, or an empty list if valid
     */
    private List<String> validate() {
        List<String> issues = new ArrayList<>();
        if (courseSemesters.isEmpty()) {
            issues.add("Year " + yearNumber + " has no semesters.");
            return issues;
        }

        Set<String> seenNames = new HashSet<>();
        for (CourseSemester s : courseSemesters) {
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
