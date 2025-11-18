package users;

public class Lecturer extends User {
    public Lecturer() {
        super("", "");
    }

    public Lecturer(String userId, String name) {
        super(userId, name);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Lecturer{name='" + name + "', userId='" + userId + "'}";
    }
}
