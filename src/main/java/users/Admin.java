package src.main.java.users;
import src.main.java.timetables.ProgramWithCourseModule;

/**
 * This is the admin class
 * It will be used for the bulk of object creations and method calls
 * It will be able to access and change timetables of both users and programs
 * User will only be instantiated once ever if the user of this software chooses admin in the CLI
 */
public class Admin extends User {

    // Don't need multiple admins, just hardcoded ID and name
    public Admin(){
        super("admin", 88888);
    }
    public void createUser(char type, int userId, ProgramWithCourseModule program){
        if(type == 't'){

        }
    }
}
