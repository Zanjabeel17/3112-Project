import java.util.ArrayList;
import java.util.List;

public abstract class User implements Comparable<User> {
    private String username;
    private String password;
    protected boolean loggedIn;
    private static List<User> allUsers = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
        allUsers.add(this); 
    }

   /**
     * @param enteredUsername the username entered during login
     * @param enteredPassword the password entered during login
     * @return true if login is successful, false otherwise
     */
    public abstract boolean login(String enteredUsername, String enteredPassword);

     /**
     * Logs the user out by setting loggedIn to false
     */
    public void logout() {
        loggedIn = false;
    }

     /**
     * Checks if the user is currently logged in.
     * @return true if the user is logged in, otherwise false
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Gets the username of the user
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param otherUser the user to compare with
     * @return 0 if the classes are the same, -1 if this is an Employee and otherUser is not, 1 if this is an Administrator and otherUser is not
     */
    @Override
    public int compareTo(User otherUser) {
        if (this.getClass() == otherUser.getClass()) {
            return 0; 
        } else if (this instanceof Employee) {
            return -1; 
        } else {
            return 1; 
        }
    }
    
    
}