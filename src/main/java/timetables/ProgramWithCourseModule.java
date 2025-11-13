package src.main.java.timetables;

import src.main.java.modules.CourseModule;

import java.util.*;

public class ProgramWithCourseModule {

        private String code;
        private String name;
        private List<ProgramWithCourseModule.ProgramYear> years;

    public ProgramWithCourseModule(String code, String name, List < ProgramWithCourseModule.ProgramYear > years)
        {
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

        public final static class ProgramYear {
            private final src.main.java.timetables.ProgramWithCourseModule programme;
            private final int yearNumber;
            private final List<ProgramWithCourseModule.ProgramSemester> semesters;

            public ProgramYear(src.main.java.timetables.ProgramWithCourseModule programme, int yearNumber, List<ProgramWithCourseModule.ProgramSemester> semesters) {
                if (semesters == null) throw new IllegalArgumentException("semesters is null");
                this.programme = programme;
                this.yearNumber = yearNumber;
                this.semesters = new ArrayList<>(semesters);
            }

            public src.main.java.timetables.ProgramWithCourseModule getProgramme() {
                return programme;
            }

            public int getYearNumber() {
                return yearNumber;
            }

            public List<ProgramWithCourseModule.ProgramSemester> getSemesters() {
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
                for (ProgramWithCourseModule.ProgramSemester s : semesters) {
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
                for (ProgramWithCourseModule.ProgramSemester s : semesters) {
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
                for (ProgramWithCourseModule.ProgramSemester s : semesters) {
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
                for (ProgramWithCourseModule.ProgramSemester s : semesters) {
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

        public static final class ProgramSemester {
            private final String name;
            private final List<CourseModule> moduleCodes;

            public ProgramSemester(String name, List<CourseModule> moduleCodes) {
                if (name == null) {
                    throw new IllegalArgumentException("name is null");

                }
                if (moduleCodes == null) {
                    throw new IllegalArgumentException("moduleCodes is null");
                }

                this.name = name;
                this.moduleCodes = new ArrayList<>(moduleCodes);
            }

            public String getName() {
                return this.name;
            }


            public List<CourseModule> getModuleCodes()
            {
                return new ArrayList<>(moduleCodes);
            }


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

}

