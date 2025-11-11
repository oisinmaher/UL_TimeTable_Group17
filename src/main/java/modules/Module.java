package modules;

import java.util.ArrayList;

public class Module {
    private String moduleName;
    private String moduleCode;
    private String lecturers;
    private int numberOfLecHours;
    private int numberOfLabs;
    private int numberOfTutorials;
    static private ArrayList<Module> listOfModules = new ArrayList<>();

    /*
     * Create module if only name and code are known. (New module).
     */
    public Module(String moduleName, String moduleCode) {
        if(checkUniqueModuleCode(moduleCode)) {
            this.moduleName = moduleName;
            this.moduleCode = moduleCode;
            listOfModules.add(this);
        } else {
            throw new IllegalArgumentException("The inputted module code already exists.");
        }   
    }

    /*
     * Create module with all data if it is known. (Existing module).
     */
    public Module(String moduleName, String moduleCode, String lecturers, 
                  int numberOfLecHours, int numberOfLabs, int numberOfTutorials) {
        if(checkUniqueModuleCode(moduleCode)) {
            this.moduleName = moduleName;
            this.moduleCode = moduleCode;
            this.lecturers = lecturers;
            this.numberOfLecHours = numberOfLecHours;
            this.numberOfLabs = numberOfLabs;
            this.numberOfTutorials = numberOfTutorials;
            listOfModules.add(this);
        } else {
            throw new IllegalArgumentException("The inputted module code already exists.");
        }
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getLecturers() {
        return lecturers;
    }

    public void setLecturers(String lecturers) {
        this.lecturers = lecturers;
    }

    public int getLecHours() {
        return numberOfLecHours;
    }

    public void setLecHours(int numberOfLecHours) {
        this.numberOfLecHours = numberOfLecHours;
    }

    public int getNumberOfLabs() {
        return numberOfLabs;
    }

    public void setNumberOfLabs(int numberOfLabs) {
        this.numberOfLabs = numberOfLabs;
    }

    public int getNumberOfTutorials() {
        return numberOfTutorials;
    }

    public void setNumberOfTutorials(int numberOfTutorials) {
        this.numberOfTutorials = numberOfTutorials;
    }

    /*
     * This might work with Times class.
     */
    public void createTutorials(int numberOfTutorials) {
        
    }

    /*
     * Returns true if the module code is unique and can be used,
     * Returns false if module code already exists. 
     */
    private static boolean checkUniqueModuleCode(String newModuleCode) {
        boolean unique = true;
        for(Module m : listOfModules) {
            if(m.moduleCode.equals(newModuleCode)) {
                return unique = false;
            }
        }
        return unique;
    }

    public ArrayList<String> getListOfModules() {
        ArrayList<String> listOfModules = new ArrayList<String>();
        for(int i = 0; i < listOfModules.size(); i++) {
            listOfModules.add(listOfModules.get(i));
        }
        return listOfModules;
    }
    
    //only returns 2nd and 3rd lines of timetable slot.
    public String toStringLec() {
        return moduleCode + " - " + "LEC" + "\n" +
               lecturers + "\n";
    }

    //labGroup is a placeholder atm.
    public String toStringLab() {
        return moduleCode + " - " + "LAB" + " - " + "labGroup" + "\n" +
               lecturers + "\n";
    }

    public String toStringTut() {
        return moduleCode + " - " + "TUT" + " - " + "labGroup" + "\n" +
               lecturers + "\n";
    }
}
/* 
public ArrayList<String> getLecturers() {
        ArrayList<String> listOfLecturers = new ArrayList<String>();
        for(int i = 0; i < lecturers.size(); i++) {
            listOfLecturers.add(lecturers.get(i));
        }
        return listOfLecturers;
    }
*/