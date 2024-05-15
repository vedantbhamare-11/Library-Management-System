import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String email;
    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public static User findById(ArrayList<User> users, int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // ID not found
    }
}
