package src.main.java.programCourse;

import src.main.java.modules.CourseModule;
import src.main.java.programCourse.CourseSemester;
import src.main.java.programCourse.CourseFull;
import src.main.java.timetables.TimeSlot;

import java.util.*;

/**
 * Represents a single academic year within a course (e.g. Year 1, Year 2).
 *
 * A CourseYear:
 *  - Belongs to a specific CourseFull
 *  - Has a year number (e.g. 1, 2, 3)
 *  - Contains a map of Season -> CourseSemester objects (e.g. Autumn, Spring)
 *
 */
public final class CourseYear{

    // year id is fullCourse.getCode() + "_" + yearNumber
    private String yearId;
    private final String yearNumber;
    //private final int yearNumber;
    private Map<String, CourseSemester> courseSemesters;
    public static Map<String, CourseYear> allCourseYears = new HashMap<>();

    /**
     * Constructs a CourseYear with a reference to CourseWithModule,
     * a year number, and a list of semesters.
     *
     * @param yearNumber the numeric identifier for the year (e.g. 1, 2, 3)
     *
     * @throws IllegalArgumentException if semesters is null
     */
    public CourseYear(CourseFull courseFull, String yearNumber) {
        if(courseFull == null){
            throw new IllegalArgumentException("Course cant be null");
        }
        this.yearId = courseFull.getCode() + "_" + yearNumber;
        if(allCourseYears.containsKey(yearId)){
            throw new IllegalArgumentException("Course Year already exists: \n " +
                    allCourseYears.get(yearId));
        }
        this.yearNumber = yearNumber;
        allCourseYears.put(yearId, this);
        this.courseSemesters = new HashMap<>();
    }
    // Passing courseCode (of CourseFull object) as string instead of object (not as safe)
    public CourseYear(String courseCode, String yearNumber) {
        this.yearId = courseCode + "_" + yearNumber;
        if(allCourseYears.containsKey(yearId)){
            throw new IllegalArgumentException("Course Year already exists: \n " +
                    allCourseYears.get(yearId));
        }
        this.yearNumber = yearNumber;
        allCourseYears.put(yearId, this);
        this.courseSemesters = new HashMap<>();
    }

    /**
     * Returns the numeric year number as String (e.g. "1" for first year).
     * @return the year number
     */
    public String getYearNumber() {
        return this.yearNumber;
    }
    /** Create a new course semester and add to mapping **/
    public void addSemester(String season){
        CourseSemester courseSemester = new CourseSemester(this, season.toLowerCase());
        courseSemesters.put(season.toLowerCase(), courseSemester);
    }
    /**
     * Checks whether this CourseYear contains a semester with the given name.
     * The comparison is case-insensitive.
     * @param name the semester name to search for
     * @return true if a semester with the given name exists, false otherwise
     */
    public boolean hasSemester(String name) {
        if (name == null) {
            return false;
        }
        String target = name.toLowerCase();
        for (String s : courseSemesters.keySet()) {
            if (s.equals(target)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a copy of the list of semesters in this year.
     * @return a new List containing the CourseSemester objects
     */
    public List<CourseSemester> getSemesters() {
        return new ArrayList<>(courseSemesters.values());
    }

   /**
     * Returns the list of CourseModule objects for a given semester name in this year.
     * This now uses CourseSemester.pickModulesForThisSemester(), which applies
     * the odd/even last digit rule based on the semester name.
     */

    public List<String> getModuleCodesForSemester(String semesterName){
        if (semesterName == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        String target = semesterName.toLowerCase();
        if(!courseSemesters.containsKey(target)){
            throw new IllegalArgumentException("This semester doesnt exist, it must be created first");
        }
        return courseSemesters.get(semesterName).getModuleCodes();
    }

    /**
     * Maps semester names to lists of student group IDs.
     * The input map should contain:
     *  - key: semester name (e.g. "Autumn")
     *  - value: list of student group IDs (e.g. ["G1A", "G1B"])
     * This method:
     *  - Validates that the provided semester names exist in this CourseYear
     *  - Returns a copy of the mapping
     *
     * @param semesterToGroupIDs a map from semester name to list of student group IDs
     * @return a new Map with the same keys and copies of the group lists
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

    public String getYearId(){
        return this.yearId;
    }

    /** hashing for sets, might not use */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseYear that = (CourseYear) o;
        return this.getYearId().equals(that.getYearId());
    }
    @Override
    public int hashCode(){
        return this.getYearId().hashCode();
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
        for (String s : courseSemesters.keySet()) {
            String key;
            if (s == null) {
                key = "";
            } else {
                key = s.toLowerCase();
            }
            if (key.isEmpty()) {
                issues.add("Year" + yearNumber + "has a semester with a blank name");
            } else if (seenNames.contains(key)) {
                issues.add("Year " + yearNumber + " has duplicate semester: " + s.toLowerCase());
            } else {
                seenNames.add(key);
            }
        }
        return issues;
    }
}
