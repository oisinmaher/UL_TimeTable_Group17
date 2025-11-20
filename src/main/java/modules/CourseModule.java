package modules;


import timetables.Group;
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
    private final Group groupAssigned;
    // This will contain all created modules in a class
    static private Map<String, CourseModule> allModules = new HashMap<>();

    /**
     * Constructor for course module
     * @param moduleName module's name (e.g "Database systems")
     * @param moduleCode module's code (e.g "cs4004")
     */
    public CourseModule(String moduleName, String moduleCode) {
        if(moduleName == null || moduleCode == null){
            throw new IllegalArgumentException("Parameters cant be null");
        }
        moduleCode = moduleCode.toLowerCase();
        if(!containsModuleCode(moduleCode)) {
            this.moduleName = moduleName;
            this.moduleCode = moduleCode;
            allModules.put(moduleCode, this);
        } else {
            throw new IllegalArgumentException("The inputted module code already exists.");
        }
        groupAssigned = new Group(this.moduleCode);
    }

    /**
     * checks if a moduleCode already exists
     * @param moduleCode the code associated with a module
     * @return boolean value; true is moduleCode exists, false otherwise
     */
    public static boolean containsModuleCode(String moduleCode){
        return allModules.containsKey(moduleCode.toLowerCase());
    }

    /**
     * static method that returns CourseModule object that already exists from its module code
     * @param moduleCode the code associated with module (e.g "cs4004")
     * @return a CourseModule object with same moduleCode
     */
    public static CourseModule getModuleFromCode(String moduleCode){
        if(!containsModuleCode(moduleCode))
            throw new IllegalArgumentException("This module code doesnt exist, it must be created first");
        return allModules.get(moduleCode.toLowerCase());
    }

    /**
     * Gets the name of the module
     * @return the name of the module
     */
    public String getModuleName() {
        return this.moduleName;
    }

    /**
     * Gets the code of the module
     * @return the module code
     */
    public String getModuleCode() {
        return this.moduleCode;
    }

    /**
     * This returns the group assigned to a module
     * @param moduleCode
     * @return
     */
    public Group getGroupAssignedFromModuleCode(String moduleCode){
        if(containsModuleCode(moduleCode)){
            return allModules.get(moduleCode).groupAssigned;
        }
        else throw new IllegalArgumentException("A group with " + moduleCode + " does not exist");
    }
    public Group getGroupAssigned(){
        return groupAssigned;
    }

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
     * returns number of labs this module has
     * @return number of labs
     */
    public int getNumberOfLabs() {
        return numberOfLabs;
    }

    /**
     * sets number of labs this module has
     * @param numberOfLabs the number of labs
     */
    public void setNumberOfLabs(int numberOfLabs) {
        this.numberOfLabs = numberOfLabs;
    }

    /**
     * returns number of tutorials this module has
     * @return number of tutorials
     */
    public int getNumberOfTutorials() {
        return numberOfTutorials;
    }

    /**
     * returns the number of tutorials this module has
     * @param numberOfTutorials the number of tutorials
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
     * static method returns list of all modules that exist
     * @return list of modules
     */
    public static ArrayList<CourseModule> getListOfModules() {
        return new ArrayList<>(allModules.values());
    }

    /**
     * static method returns list of all module codes that exist
     * @return list of modules
     */
    public static ArrayList<String> getListOfModuleCodes(){
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