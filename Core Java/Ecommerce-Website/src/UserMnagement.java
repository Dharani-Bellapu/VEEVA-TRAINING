import java.util.*;
public class UserMnagement {
   private static final HashMap<Integer,User> hm=new HashMap<>();

    public static int generateUserId() {
        int id = 1;
        while (hm.containsKey(id)) {
            id++;
        }
        return id;
    }

    public static void addUser(String name,String email,String phone,String address,int user_id){
        if (hm.containsKey(user_id)) {
            System.out.println("User ID already exists!");
            return;
        }
        else {
            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPhone(phone);
            u.setAddress(address);
            u.setUser_id(user_id);
            hm.put(user_id, u);
        }
    }
    public static void deleteUser(int user_id){
        hm.remove(user_id);
    }
    public static void modifyUser(int user_id){
        Scanner sc=new Scanner(System.in);
            User u = hm.get(user_id);

            if (u == null) {
                System.out.println("User not found!");
                return;
            }

            System.out.println("What do you want to modify?");
            System.out.println("1. Name");
            System.out.println("2. Email");
            System.out.println("3. Phone");
            System.out.println("4. Address");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.println("Enter new name:");
                    u.setName(sc.nextLine());
                    break;

                case 2:
                    System.out.println("Enter new email:");
                    u.setEmail(sc.nextLine());
                    break;

                case 3:
                    System.out.println("Enter new phone:");
                    u.setPhone(sc.nextLine());
                    break;

                case 4:
                    System.out.println("Enter new address:");
                    u.setAddress(sc.nextLine());
                    break;

                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            System.out.println("User updated successfully!");
        }
    public static User getUserById(int user_id) {
        return hm.get(user_id);
    }
    public static ArrayList<User> searchByName(String name) {
        ArrayList<User> result = new ArrayList<>();

        for (User u : hm.values()) {
            if (u.getName().equalsIgnoreCase(name)) {
                result.add(u);
            }
        }

        return result;
    }
    public static ArrayList<User> searchByCity(String city) {
        ArrayList<User> result = new ArrayList<>();

        for (User u : hm.values()) {
            if (u.getAddress().toLowerCase()
                    .contains(city.toLowerCase())) {
                result.add(u);
            }
        }

        return result;
    }
    public static User searchByPhone(String phone) {
        for (User u : hm.values()) {
            if (u.getPhone().equals(phone)) {
                return u;
            }
        }

        return null;
    }
    public static User searchByEmail(String email) {
        for (User u : hm.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }

        return null;
    }
}
