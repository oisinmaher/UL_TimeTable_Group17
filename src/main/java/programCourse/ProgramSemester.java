package src.main.java.programCourse;

import src.main.java.modules.CourseModule;
import java.util.*;

public final class ProgramSemester { // CHANGED: was nested static class

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

    public List<CourseModule> getModuleCodes() {
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

    public List<CourseModule> pickModulesForThisSemester()
    {
        List<CourseModule> selected = new ArrayList<>(); 

        String sem = name.toLowerCase(); 
        for(CourseModule module: moduleCodes)
        {
            String code = module.getModuleCode(); 
            if (code == null || code.isEmpty()) 
            {
                continue; 
            }
            char lastChar = code.charAt(code.length() - 1);
            if (!Character.isDigit(lastChar)) {
            continue; 
            int lastDigit = Character.getNumericValue(lastChar);

            if (sem.contains("autumn") || sem.contains("fall")) {
            // pick odd-ending module codes as per UL criteria
                if (lastDigit % 2 == 1) {
                    selected.add(module);
                }
            }
            else if(sem.contains("spring")) {
            // pick even-ending module codes
                if (lastDigit % 2 == 0) {
                    selected.add(module);
                }
            }
            else if(sem.contains("summer")) 
            {
                selected.add(module); 
            } 
            else 
            {
                throw new IllegalArgumentException("\"Invalid semester: '\" + name + \"'. Only Autumn/Fall and Spring are supported.\""); 
            }
       
        }

        }
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
