package src.main.java.users;

/**
 *
 */
class User{
    String name;
    int userId;

    /**
     *
     * @param name
     * @param userId
     */
    public User(String name, int userId){
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
    public int getUserId(){
        return this.userId;
    }

}