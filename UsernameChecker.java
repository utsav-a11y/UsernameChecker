import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsernameChecker {

    // Stores username -> userId
    private HashMap<String, Integer> users;

    // Tracks how many times a username was attempted
    private HashMap<String, Integer> attemptCount;

    public UsernameChecker() {
        users = new HashMap<>();
        attemptCount = new HashMap<>();
    }

    // Check if username is available
    public boolean checkAvailability(String username) {
        // Increase attempt count
        attemptCount.put(username,
                attemptCount.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }

    // Register a new user
    public void registerUser(String username, int userId) {
        users.put(username, userId);
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            String newName = username + i;
            if (!users.containsKey(newName)) {
                suggestions.add(newName);
            }
        }

        // Add dot variation
        String dotName = username.replace("_", ".");
        if (!users.containsKey(dotName)) {
            suggestions.add(dotName);
        }

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {
        String mostAttempted = null;
        int max = 0;

        for (String username : attemptCount.keySet()) {
            int count = attemptCount.get(username);
            if (count > max) {
                max = count;
                mostAttempted = username;
            }
        }

        return mostAttempted;
    }

    // Main method for testing
    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();

        checker.registerUser("john_doe", 1);
        checker.registerUser("alice", 2);

        System.out.println("Is john_doe available? " +
                checker.checkAvailability("john_doe"));

        System.out.println("Is jane_smith available? " +
                checker.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe: " +
                checker.suggestAlternatives("john_doe"));

        System.out.println("Most attempted username: " +
                checker.getMostAttempted());
    }
}
