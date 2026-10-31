
public class AuthenticationService {
    private static User loggedInUser = null;
    public static User register(String name,String email,String phone,String address,String password) {
        if (name.isBlank() || email.isBlank() || phone.isBlank() || address.isBlank() || password.isBlank()) {
            System.out.println("All fields are required!");
            return null;
        }
        if (UserMnagement.searchByEmail(email) != null) {
            System.out.println("Email already registered!");
            return null;
        }
        int userId = UserMnagement.generateUserId();
        UserMnagement.addUser(name,email,phone,address,userId);
        User user = UserMnagement.getUserById(userId);
        if (user != null) {
            user.setPassword(password);
            System.out.println("Registration successful! Your User ID is: " + userId);
        }
        return user;
    }
    public static User login(String email,String password) {
        if (loggedInUser != null) {
            System.out.println("Another user is already logged in!");
            return null;
        }
        User user = UserMnagement.searchByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        loggedInUser = user;
        return loggedInUser;
    }
    public static void logout() {
        loggedInUser = null;
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
