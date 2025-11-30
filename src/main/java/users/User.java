package users;

import timetables.TimeSlot;
import timetables.UserTimeTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class User{
    String name;
    String userId;
    UserTimeTable userTimeTable;
    private static Map<String, User> allUsers = new HashMap<>();

    /**
     *
     * @param name
     * @param userId
     */
    public User(String userId, String name){
        this.name = name;
        this.userId = userId;
        this.userTimeTable = new UserTimeTable(this);
    }
    public boolean containsUser(String userId){
        return allUsers.containsKey(userId);
    }
    public User getUser(String userId){
        if(containsUser(userId))
            return allUsers.get(userId);
        throw new IllegalArgumentException("This user doesn't exist");
    }


    public UserTimeTable getUserTimeTable(){
        return this.userTimeTable;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     *
     * @return
     */
    public String getUserId(){
        return this.userId;
    }

}