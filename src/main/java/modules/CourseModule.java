package modules;



import users.Lecturer;

import java.util.*;

/**
 * Course Module
 */

public class CourseModule {
    private final String moduleName;
    private final String moduleCode;
    private List<Lecturer> lecturers;
    private int numberOfLecHours;
    private int numberOfLabs;
    private int numberOfTutorials;
    // This will contain all created modules in a class
    static private Map<String, CourseModule> allModules = new HashMap<>();

    /**
     * @param moduleName
     * @param moduleCode
     */
    public CourseModule(String moduleName, String moduleCode) {
        if(moduleName == null || moduleCode == null){
            throw new IllegalArgumentException("Parameters cant be null");
        }
        moduleCode = moduleCode.toLowerCase();
        if(checkUniqueModuleCode(moduleCode)) {
            this.moduleName = moduleName;
            this.moduleCode = moduleCode;
            allModules.put(moduleCode, this);
        } else {
            throw new IllegalArgumentException("The inputted module code already exists.");
        }   
    }

//    /**
//     * Creates the course module object
//     * @param moduleName the name of the module
//     * @param moduleCode the module code
//     * @param lecturers the module lecturer
//     * @param numberOfLecHours the number of lecture hours required
//     * @param numberOfLabs the number of labs required
//     * @param numberOfTutorials the number of tutorials required
//     */
//    public CourseModule(String moduleName, String moduleCode, String lecturers,
//                        int numberOfLecHours, int numberOfLabs, int numberOfTutorials) {
//        moduleCode = moduleCode.toLowerCase();
//        if(checkUniqueModuleCode(moduleCode)) {
//            this.moduleName = moduleName;
//            this.moduleCode = moduleCode;
//            this.lecturers = new ArrayList<>();
//            this.numberOfLecHours = numberOfLecHours;
//            this.numberOfLabs = numberOfLabs;
//            this.numberOfTutorials = numberOfTutorials;
//            allModules.put(moduleCode, this);
//        } else {
//            throw new IllegalArgumentException("The inputted module code already exists.");
//        }
//    }
    public static boolean checkModuleCode(String moduleCode){
        return allModules.containsKey(moduleCode.toLowerCase());
    }
    public static CourseModule getModuleFromCode(String moduleCode){
        if(!checkModuleCode(moduleCode))
            throw new IllegalArgumentException("This module code doesnt exist, it must be created first");
        return allModules.get(moduleCode.toLowerCase());
    }

    /**
     * Gets the name of the module
     * @return the name of the module
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Gets the code of the module
     * @return the module code
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Gets the name of the lecturer
     * @return the name of the lecturer
     */
//    public String getLecturers() {
//        return lecturers;
//    }

    /**
     * Sets the name of the lecturer
     * @param lecturers the name of the module's lecturer
     */
//    public void setLecturers(String lecturers) {
//        this.lecturers = lecturers;
//    }

    /**
     * Gets the number of required lecture hours
     * @return the number of lecture hours
     */
    public int getLecHours() {
        return numberOfLecHours;
    }

    /**
     * Sets the number of lecture hours
     * @param numberOfLecHours the number of hours required by lecturer
     */
    public void setLecHours(int numberOfLecHours) {
        this.numberOfLecHours = numberOfLecHours;
    }

    /**
     *
     * @return
     */
    public int getNumberOfLabs() {
        return numberOfLabs;
    }

    /**
     *
     * @param numberOfLabs
     */
    public void setNumberOfLabs(int numberOfLabs) {
        this.numberOfLabs = numberOfLabs;
    }

    /**
     *
     * @return
     */
    public int getNumberOfTutorials() {
        return numberOfTutorials;
    }

    /**
     *
     * @param numberOfTutorials
     */
    public void setNumberOfTutorials(int numberOfTutorials) {
        this.numberOfTutorials = numberOfTutorials;
    }

    /**
     *
     * @param numberOfTutorials
     */
    public void createTutorials(int numberOfTutorials) {

    }

    /**
     *
     * @param newModuleCode
     * @return
     */
    private static boolean checkUniqueModuleCode(String newModuleCode) {
        return (!allModules.containsKey(newModuleCode));
    }


    public ArrayList<CourseModule> getListOfModules() {
        return new ArrayList<>(allModules.values());
    }
    public ArrayList<String> getListOfModuleCodes(){
        return new ArrayList<>(allModules.keySet());
    }

    @Override
    public String toString(){
        return moduleCode + " - " + moduleName;
    }

    /*
     *
     * @return
     *
    public String toStringLec() {
        return moduleCode + " - " + "LEC" + "\n" +
               lecturers + "\n";
    }

    /*
     *
     * @return
     *
    public String toStringLab() {
        return moduleCode + " - " + "LAB" + " - " + "labGroup" + "\n" +
               lecturers + "\n";
    }

    /**
     *
     * @return
     *
    public String toStringTut() {
        return moduleCode + " - " + "TUT" + " - " + "labGroup" + "\n" +
               lecturers + "\n";


    }

     */
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