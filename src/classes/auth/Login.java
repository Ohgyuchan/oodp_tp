package classes.auth;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.simple.parser.ParseException;

import classes.SingletonJSON;
import classes.User;

// Mediator
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
        
        
        ArrayList<User> users = new ArrayList<>();
        SingletonJSON.getInstance();
        users = SingletonJSON.getInstance().getUserList();

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
