import src.main.java.Program;

import java.util.*;
 
// Main src.main.java.Program class representing a degree program.
// Holds a code, a name, and a list of ProgramYear objects.
public class ProgramWithComments
{
    private String code; 
    private String name; 
    private List<ProgramYear> years; 

    // Constructor for src.main.java.Program.
    // Validates that code, name, and years are not null, then copies the list of years.
    public ProgramWithComments(String code, String name, List<ProgramYear> years)
    {
        if(code == null)
        {
            throw new IllegalArgumentException("Code can't be null"); 
        }
        if(name == null)
        {
            throw new IllegalArgumentException("Name can't be null"); 
        }
        if(years == null)
        {
            throw new IllegalArgumentException("Years can't be null"); 
        }
        this.code = code; 
        this.name = name; 
        this.years = new ArrayList<>(years); 
    }

    // Nested class representing a specific year within a src.main.java.Program (e.g. Year 1, Year 2).
    // Holds a reference to the parent src.main.java.Program, the year number, and a list of semesters.
    public final static class ProgramYear
    {
        private final Program programme; 
        private final int yearNumber; 
        private final List<ProgramSemester> semesters; 

        // Constructor for ProgramYear.
        // Validates that semesters is not null, then copies the list.
        public ProgramYear(Program programme, int yearNumber, List<ProgramSemester>semesters)
        {
            if (semesters == null) throw new IllegalArgumentException("semesters is null");
            this.programme = programme;
            this.yearNumber = yearNumber;
            this.semesters = new ArrayList<>(semesters);
        }

        // Returns the parent src.main.java.Program this year belongs to.
        public Program getProgramme() 
        {
            return programme; 
        }

        // Returns the numeric year value (e.g., 1, 2, 3).
        public int getYearNumber() 
        { 
            return yearNumber;
        }

        // Returns a copy of the list of semesters for this year.
        public List<ProgramSemester> getSemesters() 
        {
            return new ArrayList<>(semesters); 
        }

        // Returns the list of modules for a given semester name (case-insensitive).
        // If no semester matches, returns an empty list.
        public List<String> modulesForSemester(String semesterName)
        {
            if(semesterName == null)
            {
                return new ArrayList<>(); 
            }
            String target = semesterName.toLowerCase(); 
            for(ProgramSemester s : semesters)
            {
                if(s.getName().toLowerCase().equals(target))
                {
                    return new ArrayList<>(s.getModuleCodes()); 
                }
            }
            return new ArrayList<>(); 
        }

        // Returns all modules across all semesters in this year, without duplicates.
        public List<String> allModules()
        {
            List<String> out = new ArrayList<>(); 
            Set<String> seen = new HashSet<>(); 
            for(ProgramSemester s:semesters)
            {
                for(String m: s.getModuleCodes())
                {
                    if(!seen.contains(m))
                    {
                        seen.add(m); 
                        out.add(m); 
                    }
                }
            }
            return out; 
        }

        // Maps semester names to lists of student group IDs.
        // Validates that each semester name exists before adding it to the map.
        public Map<String, List<String>> mapToStudentGroups(Map <String, List<String>> semesterToGroupIDs)
        {
            if(semesterToGroupIDs == null)
            {
                throw new IllegalArgumentException("SemesterToGroupIDs can't be null");
            }
            Map<String, List<String>> result = new HashMap<>();
            for(Map.Entry<String, List<String>> e : semesterToGroupIDs.entrySet())
            {
                String semKey = e.getKey();
                if(!hasSemester(semKey))
                {
                    throw new IllegalArgumentException("Unknown semester for year " + yearNumber + ": " + semKey); 
                }
                List<String> groups = new ArrayList<>(e.getValue()); 
                result.put(semKey, groups); 
            } 
            return result; 
        }

        // Checks whether this ProgramYear contains a semester with the given name.
        // Case-insensitive comparison.
        public boolean hasSemester(String name)
        {
            if (name == null)
            {
                return false; 
            }
            String target = name.toLowerCase(); 
            for(ProgramSemester s: semesters)
            {
                if(s.getName().toLowerCase().equals(target))
                {
                    return true;
                }
            }
            return false; 
        }

        // Validates the ProgramYear by checking:
        // - that it has semesters
        // - that semester names are not blank
        // - that semester names are not duplicated
        // - that each semester is valid
        // Returns a list of error messages (empty means no problems).
        private List<String> validate()
        {
            List <String> issues = new ArrayList<>(); 
            if (semesters.isEmpty()) {
                issues.add("Year " + yearNumber + " has no semesters.");
                return issues;
            }
            
            Set <String> seenNames = new HashSet<>(); 
            for(ProgramSemester s: semesters)
            {
                String key; 
                if(s.getName() == null)
                {
                    key = ""; 
                }  
                else 
                {
                    key = s.getName().toLowerCase(); 
                }

                if(key.isEmpty())
                {
                    issues.add("Year" + yearNumber + "has a semester with a blank name"); 
                }
                else if(seenNames.contains(key))
                {
                    issues.add("Year " + yearNumber + " has duplicate semester: " + s.getName());
                }
                else 
                {
                    seenNames.add(key);
                }
                issues.addAll(s.validate(yearNumber)); 
            }
            return issues; 
        }
    }

    // Represents a single semester (e.g. Autumn, Spring).
    // Holds the semester's name and the module codes in that semester.
    public static final class ProgramSemester
    {
        private final String name;
        private final List<String> moduleCodes; 

        // Constructor for ProgramSemester.
        // Validates that name and moduleCodes are not null, then copies the module list.
        public ProgramSemester(String name, List<String> moduleCodes) {
            if (name == null) 
            {
                throw new IllegalArgumentException("name is null");
            
            }    
            if (moduleCodes == null) 
            {
                throw new IllegalArgumentException("moduleCodes is null");
            }
            
            this.name = name;
            this.moduleCodes = new ArrayList<>(moduleCodes);
        } 

        // Returns the name of this semester.
        public String getName()
        {
            return this.name;
        }

        // Returns a copy of the list of module codes in this semester.
        public List<String> getModuleCodes()
        {
            return new ArrayList<>(moduleCodes); 
        }

        // Validates this semester by checking:
        // - the name is not blank
        // - there is at least one module
        // - no module codes are null or blank
        // - no module codes are duplicated
        private List<String> validate(int yearNumber)
        {
            List<String> issues = new ArrayList<>(); 
            if(name.isEmpty())
            {
                issues.add("Year" + yearNumber+ " has a semester with a blank name"); 
            }
            if(moduleCodes.isEmpty())
            {
                issues.add("Year" + yearNumber +" has no modules listed"); 
            }
            Set <String> seen = new HashSet<>(); 
            for(String m:moduleCodes)
            {
                if(m == null || m.isEmpty())
                {
                    issues.add("Year" + yearNumber+ " has a blank module code"); 
                }
                else if(seen.contains(m))
                {
                    issues.add("Year" + yearNumber + " has a module that's already listed"); 
                }
                else 
                {
                    seen.add(m); 
                }
            }
            return issues; 
        }
        
        // Creates a formatted string showing the semester name and all its modules.
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
