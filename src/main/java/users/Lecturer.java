package users;

public class Lecturer extends User{
    String name;
    String userId;
    public Lecturer(String userId, String name){
        super(userId, name);
    }
    public void setName(String name){
        this.name = name;
    }


}
