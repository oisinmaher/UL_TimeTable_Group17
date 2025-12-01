package users;

import timetables.TimeSlot;
import timetables.UserTimeTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User class.
 * Contains:
 *      - Map of user ID to User object (allUsers)
 */
public class User{
    String name;
    String userId;
    UserTimeTable userTimeTable;
    private static Map<String, User> allUsers = new HashMap<>();

    /**
     * Constructs Users along with a corresponding user timetable
     * @param name users name
     * @param userId users ID number
     */
    public User(String userId, String name){
        this.name = name;
        this.userId = userId;
        this.userTimeTable = new UserTimeTable(this);
    }

    /**
     * Checks if a given user ID already exists or not
     * @param userId user ID number
     * @return true if user ID exists, false otherwise
     */
    public boolean containsUser(String userId){
        return allUsers.containsKey(userId);
    }

    /**
     * Gets the User object corresponding with a given user ID
     * @param userId users ID number
     * @return User object
     * @throws IllegalArgumentException if user ID doesn't exist
     */
    public User getUser(String userId){
        if(containsUser(userId))
            return allUsers.get(userId);
        throw new IllegalArgumentException("This user doesn't exist");
    }

    /**
     * Gets the users timetable
     * @return Users timetable as an object
     */
    public UserTimeTable getUserTimeTable(){
        return this.userTimeTable;
    }

    /**
     * Set the name of the user
     * @param name name of the user
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the users ID number
     * @param userId users ID number
     */
    public void setUserId(String userId){
        this.userId = userId;
    }

    /**
     * Gets the users name
     * @return the name of the user
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the users ID number
     * @return user ID number
     */
    public String getUserId(){
        return this.userId;
    }
}