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
 *  - Is NOT mutually referred to the CourseYear
 */
public final class CourseSemester {
    // Semester id = CourseYearId + "_" + season
    private final String semesterId;
    // spring/autumn
    private final String season;
    // module code -> Module object.  "cs4404" -> CourseModule Object
    private final Map<String, CourseModule> assignedModules;
    private static final Map<String, CourseSemester> allSemesters = new HashMap<>();
    private CourseFull courseFull;

    /**
     * enum for  2 seasons (probably won't use as I cant really think how cli would even benefit with enums
     * if you have any idea let me (Oisin) know
     */
//    public enum Seasons {
//        SPRING,
//        AUTUMN
//    }

    /**
     * Creates a CourseSemester with a name and list of module codes.
     * @param season the name of the semester (e.g. "Autumn")
     * @throws IllegalArgumentException if name is null
     *
     * NOTE, as of Right now no mutual reference to the CourseYear its assigned
     */
    public CourseSemester(CourseYear courseYear, String season, CourseFull courseFull) {
        if (season == null) {
            throw new IllegalArgumentException("season is null");
        }
        season = season.toLowerCase();
        // if neither spring nor autumn
        if (!(season.equals("spring") || season.equals("autumn"))) {
            throw new IllegalArgumentException("Season must be spring or autumn");
        }
        this.semesterId = courseYear.getYearId() + "_" + season;
        this.season = season;
        allSemesters.put(semesterId, this);
        this.assignedModules = new HashMap<>();
        this.courseFull = courseFull;
    }
    public String getSemesterId(){
        return this.semesterId;
    }

    /**
     *  Checks the STATIC allSemester map
     * @param semesterId the semester id will be courseId + season
     * @return CourseSemester object associated with semester id
     */
    public static CourseSemester getSemesterById(String semesterId){
        if(!allSemesters.containsKey(semesterId)){
            throw new IllegalArgumentException("This semesterId doesnt exist");
        }
        return allSemesters.get(semesterId);
    }

    /**
     *  Adds a module that's already been created (if a different course shares same module)
     * @param moduleCode name of a module (its code)
     */
    public void addExistingModule(String moduleCode){
        assignedModules.put(moduleCode.toLowerCase(), CourseModule.getModuleFromCode(moduleCode));
    }

    /** Creates new Course module
     * CourseModule constructor already has a check for existing modules
     * @param code // module code e.g cs4004
     * @param name // module name e.g Database Systems
     */
    public void addNewModule(String code, String name){
        CourseModule module = new CourseModule(name.toLowerCase(), code, courseFull);
        assignedModules.put(code.toLowerCase(), module);
    }

    /**
     * @return semester name as a String
     */
    public String getSeason() {
        return this.season;
    }

    /**
     * Returns a defensive copy of the list of modules assigned to this semester.
     * @return list of CourseModule objects
     */
    public List<String> getModuleCodes() {
        return new ArrayList<>(assignedModules.keySet());
    }

    public List<CourseModule> getModuleObjects(){
        return new ArrayList<>(assignedModules.values());
    }

    public CourseModule getModuleFromCode(String moduleCode){
        moduleCode = moduleCode.toLowerCase();
        return assignedModules.get(moduleCode);
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


    /** hashing for sets, might not use */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSemester that = (CourseSemester) o;
        return this.getSemesterId().equals(that.getSemesterId());
    }
    @Override
    public int hashCode(){
        return this.getSemesterId().hashCode();
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
