package classes.auth;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import classes.User;

public class Login {
    private boolean isLogin = false;
    private User currentUser = new User();

    private void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean getIsLogin() {
        return this.isLogin;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Login() {

    }

    public void login(Scanner sc)
            throws JsonMappingException, JsonProcessingException, FileNotFoundException, IOException, ParseException {
        Object userData = new JSONParser().parse(new FileReader("src/assets/data/users_data.json"));

        JSONObject jsonUser = (JSONObject) userData;
        JSONArray jsonUsers = (JSONArray) jsonUser.get("users");
        
        ArrayList<User> users = new ArrayList<>();
        
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < jsonUsers.size(); i++) {
            User user = mapper.readValue(jsonUsers.get(i).toString(), User.class);
            users.add(user);
        }

        String uid;
        String upw;

        System.out.print("Input your ID: ");
        uid = sc.next();
        System.out.print("Input your Password: ");
        upw = sc.next();

        for (User user : users) {
            if (user.getId().equals(uid)) {
                if (user.getPassword().equals(upw)) {
                    System.out.println(user.getDisplayName() + " Login Success");
                    setCurrentUser(user);
                    setIsLogin(true);
                    break;
                }
            }
        }
    }
}
