package src.main.java.timetables;

import src.main.java.modules.CourseModule;
import java.util.*;

public final class ProgramYear { // CHANGED: was nested static class

    private final ProgramWithModule programme;
    private final int yearNumber;
    private final List<ProgramSemester> semesters; // CHANGED: was List<ProgramWithCourseModule.ProgramSemester>

    public ProgramYear(ProgramWithModule programme, int yearNumber, List<ProgramSemester> semesters) { // CHANGED: types
        if (semesters == null) throw new IllegalArgumentException("semesters is null");
        this.programme = programme;
        this.yearNumber = yearNumber;
        this.semesters = new ArrayList<>(semesters);
    }

    public ProgramWithModule getProgramme() {
        return programme;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public List<ProgramSemester> getSemesters() {
        return new ArrayList<>(semesters);
    }

    /**
     * Find all module codes for a year (all semesters combined, no duplicates).
     */
    public List<CourseModule> modulesForSemester(String semesterName) {
        if (semesterName == null) {
            return new ArrayList<>();
        }
        String target = semesterName.toLowerCase();
        for (ProgramSemester s : semesters) { // CHANGED: type name
            if (s.getName().toLowerCase().equals(target)) {
                return new ArrayList<>(s.getModuleCodes());
            }
        }
        return new ArrayList<>();
    }

    /**
     * All modules across all semesters in this year (deduped).
     */
    public List<CourseModule> allModules() {
        List<CourseModule> out = new ArrayList<>();
        Set<CourseModule> seen = new HashSet<>();
        for (ProgramSemester s : semesters) { // CHANGED: type name
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
     * Map semester name → student group IDs
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

    public boolean hasSemester(String name) {
        if (name == null) {
            return false;
        }
        String target = name.toLowerCase();
        for (ProgramSemester s : semesters) { // CHANGED: type name
            if (s.getName().toLowerCase().equals(target)) {
                return true;
            }
        }
        return false;
    }

    private List<String> validate() {
        List<String> issues = new ArrayList<>();
        if (semesters.isEmpty()) {
            issues.add("Year " + yearNumber + " has no semesters.");
            return issues;
        }

        Set<String> seenNames = new HashSet<>();
        for (ProgramSemester s : semesters) { // CHANGED: type name
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
