import java.util.HashMap;
import java.util.Map;

public class Authenticator {

        private Map<String, String> users = new HashMap<>();

        public Authenticator() {
            // Predefined users for simplicity
            users.put("user1", "password1");
            users.put("user2", "password2");
            users.put("", "");

        }

        public boolean authenticate(String username, String password) {
            return users.containsKey(username) && users.get(username).equals(password);
        }

}
