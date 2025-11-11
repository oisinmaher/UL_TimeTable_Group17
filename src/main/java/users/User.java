package src.main.java.users;

class User{
    String name;
    int userId;
    public User(String name, int userId){
        this.name = name;
        this.userId = userId;
    }
    public String getName(){
        return this.name;
    }
    public int getUserId(){
        return this.userId;
    }

}