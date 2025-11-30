package users;

import modules.CourseModule;

import java.util.*;

/** Teacher class.
 *  This class inherits User
 *  It will be quite similar to Student but with modules from different courses
 *  Contains:
 *      - Map of teacher ID to Teacher objects (allTeachers)
 *      - Map of module code to CourseModule objects (modulesTaught)
 */
public class Teacher extends User {

    private final static Map<String, Teacher> allTeachers = new HashMap<>();
    // List of modules the teacher teaches (e.g. CS4141, CS4416 etc.)
    private final Map<String, CourseModule> modulesTaught;

    /**
     * Constructor with base parameters of user
     * @param teacherId teachers ID number
     * @param name name of the teacher
     */
    public Teacher(String teacherId, String name) {
        super(teacherId, name);
        modulesTaught = new HashMap<>();
        allTeachers.put(this.userId, this);
    }

    /**
     * Adds modules to a map of modules that this teacher teaches
     * @param moduleCode code of a module
     */
    public void addModuleToTeacher(String moduleCode){
        moduleCode = moduleCode.toLowerCase();
        CourseModule courseModule = CourseModule.getModuleFromCode(moduleCode);
        modulesTaught.put(moduleCode, courseModule);
    }

    /**
     * Removes a module from the modules that the teacher teaches
     * @param moduleCode code of a module
     * @throws IllegalArgumentException if the given module code does not match with any of
     *                                  the current modules that the teacher teaches
     */
    public void removeModuleFromTeacher(String moduleCode){
        moduleCode = moduleCode.toLowerCase();
        if(containsModule(moduleCode)){
            modulesTaught.remove(moduleCode);
        }
        else{
            throw new IllegalArgumentException("Teacher doesn't teach this module");
        }
    }

    /**
     * Checks if this teacher teaches a certain module, given by the module code
     * @param moduleCode code of a module
     * @return true if the teacher teaches this module, false otherwise
     */
    private boolean containsModule(String moduleCode){
        return modulesTaught.containsKey(moduleCode);
    }

    /**
     * Checks if the given teacher ID exists
     * @param teacherId teachers ID number
     * @return true if the teacher ID exists, false otherwise
     */
    private static boolean checkTeacherId(String teacherId) {
        return allTeachers.containsKey(teacherId);
    }

    /**
     * Gets the Teacher object if the given teacher ID exists
     * @param teacherId teachers ID number
     * @return Teacher object
     * @throws IllegalArgumentException if the teacher ID doesn't exist
     */
    public static Teacher getTeacherFromId(String teacherId) {
        if(checkTeacherId(teacherId))
            return allTeachers.get(teacherId);
        else throw new IllegalArgumentException("This Teacher ID " + teacherId + " does not exist");
    }

    /**
     * toString of the teachers name
     * @return name of the teacher
     */
    @Override
    public String toString(){
        return getName();
    }
}
