package users;

import com.opencsv.bean.CsvBindByName;
import timetables.Group;

/**
 *
 */
public class User extends Group {

    String name;
    String userId;

    /**
     *
     * @param name
     * @param userId
     */
    public User(String userId, String name){
        this.name = name;
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