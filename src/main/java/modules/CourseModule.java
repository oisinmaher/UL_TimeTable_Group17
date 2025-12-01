package modules;

import adminPanel1.DataManager;
import programCourse.CourseFull;
import timetables.CourseTimeTable;
import timetables.Group;
import timetables.TimeSlot;
import users.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Course Module
 */

public class CourseModule {
    private List<CourseFull> coursesTakingThisModule = new ArrayList<>();
    private final String moduleName;
    private final String moduleCode;
    private List<Teacher> lecturers;
    private int LecHours;
    private int labHours;
    private int tutHours;
    private final Group groupAssigned;
    // This will contain all created modules in a class
    static private Map<String, CourseModule> allModules = new HashMap<>();
    /**
     * Constructor for course module
     * @param moduleName module's name (e.g "Database systems")
     * @param moduleCode module's code (e.g "cs4004")
     */
    public CourseModule(String moduleName, String moduleCode, CourseFull courseFull) {
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
        this.coursesTakingThisModule = new ArrayList<>();
        this.coursesTakingThisModule.add(courseFull);
        groupAssigned = new Group(this, this.coursesTakingThisModule);
        DataManager.groups.put(this.moduleCode, groupAssigned);
        this.LecHours = 0;
        this.labHours = 0;
        this.tutHours = 0;
    }

    public void addCourse(String courseCode){
        CourseFull courseFull = CourseFull.getCourseFromCode(courseCode);
        CourseTimeTable courseTimeTable = courseFull.getCourseTimeTable();
        for(TimeSlot ts : groupAssigned.getTimeSlots()){
            courseTimeTable.addTimeSlot(ts.getTime(), ts);
        }
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

    public static Map<String, CourseModule> getModuleMap(){
        return allModules;
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
        return LecHours;
    }

    /**
     * Sets the number of lecture hours, Group class handles creating/deleting lectures
     * Group will call this along with getLecHours to increment/decrement by 1
     * @param numberOfLecHours the number of hours required by lecturer
     */
    public void setLecHours(int numberOfLecHours) {
        this.LecHours = numberOfLecHours;
    }

    /**
     * returns number of labs this module has
     * @return number of labs
     */
    public int getLabHours() {
        return labHours;
    }

    /**
     * Sets the number of lab hours, Group class handles creating/deleting labs
     * Group will call this along with getLabHours to increment/decrement by 1
     * @param labHours the number of labs
     */
    public void setLabHours(int labHours) {
        this.labHours = labHours;
    }

    /**
     * returns number of tutorials this module has
     * @return number of tutorials
     */
    public int getTutHours() {
        return tutHours;
    }
    /**
     * Sets the number of tutorial hours, Group class handles creating/deleting tutorials
     * Group will call this along with getTutHours to increment/decrement by 1
     * @param tutHours the new number of tutorials
     */
    public void setTutHours(int tutHours){
        this.tutHours = tutHours;
    }

    /**
     * static method returns list of all modules that exist
     * @return list of modules
     */
    public static List<CourseModule> getListOfModules() {
        return new ArrayList<>(allModules.values());
    }

    /**
     * static method returns list of all module codes that exist
     * @return list of modules
     */
    public static List<String> getListOfModuleCodes(){
        return new ArrayList<>(allModules.keySet());
    }

    @Override
    public String toString(){
        return moduleCode + " - " + moduleName;
    }
}
