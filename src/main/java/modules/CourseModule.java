package modules;

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
 * Course Module class
 * Contains:
 *      - add course to a course timetable
 *      - checks for a unique module code
 *      - retrieve a module given a module code
 *      - toString for module name and code
 *
 * Getters: moduleName, moduleCode, groupAssignedToModule (with or without moduleCode),
 *          LecHours, LabHours, TutHours, ListOfModules, ListOfModuleCodes
 *
 * Setters: LecHours, LabHours, TutHours
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
     * @param courseFull university course (e.g "Computer Systems")
     * @throws IllegalArgumentException if module name or code are not given
     * @throws IllegalArgumentException if the given module code is not unique
     */
    public CourseModule(String moduleName, String moduleCode, CourseFull courseFull) {
        if(moduleName == null || moduleCode == null){
            throw new IllegalArgumentException("Module name or module code can't be null");
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
        this.LecHours = 0;
        this.labHours = 0;
        this.tutHours = 0;
    }

    /**
     * Adds a course to the course timetable
     * @param courseCode the code associated with a course
     */
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
     * @return boolean value; true is moduleCode already exists, false otherwise
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
            throw new IllegalArgumentException("This module code doesn't exist, it must be created first");
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
     * Given a module code, this method returns the group of students taking a module. These students can
     * be from different courses, as some modules are shared between different courses.
     * @param moduleCode a modules code
     * @return Group assigned to a module
     * @throws IllegalArgumentException if the given module doesn't have a group of students
     */
    public Group getGroupAssignedFromModuleCode(String moduleCode){
        if(containsModuleCode(moduleCode)){
            return allModules.get(moduleCode).groupAssigned;
        }
        else throw new IllegalArgumentException("A group with " + moduleCode + " does not exist");
    }

    /**
     * This method returns the group of students taking a module. These students can be from
     * different courses, as some modules are shared between different courses.
     * @return Group assigned to a module
     */
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
     * @param numberOfLecHours the new number of lecture hours
     */
    public void setLecHours(int numberOfLecHours) {
        this.LecHours = numberOfLecHours;
    }

    /**
     * Gets the number of required lab hours
     * @return the number of lab hours
     */
    public int getLabHours() {
        return labHours;
    }

    /**
     * Sets the number of lab hours, Group class handles creating/deleting labs
     * Group will call this along with getLabHours to increment/decrement by 1
     * @param labHours the new number of lab hours
     */
    public void setLabHours(int labHours) {
        this.labHours = labHours;
    }

    /**
     * Gets the number of required tutorial hours
     * @return the number of tutorial hours
     */
    public int getTutHours() {
        return tutHours;
    }

    /**
     * Sets the number of tutorial hours, Group class handles creating/deleting tutorials
     * Group will call this along with getTutHours to increment/decrement by 1
     * @param tutHours the new number of tutorial hours
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
     * @return list of module codes
     */
    public static List<String> getListOfModuleCodes() {
        return new ArrayList<>(allModules.keySet());
    }

    /**
     * toString for a module
     * @return "moduleCode - moduleName"
     */
    @Override
    public String toString(){
        return moduleCode + " - " + moduleName;
    }
}
