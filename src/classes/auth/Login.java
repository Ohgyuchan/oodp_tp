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
    private boolean isLogin;

    public boolean getIsLogin() {
        return this.isLogin;
    }

    public Login() {
        try {
            this.isLogin = this.login();
        } catch (IOException | ParseException e) {
            this.isLogin = false;
            e.printStackTrace();
        }
    }

    private boolean login() throws JsonMappingException, JsonProcessingException, FileNotFoundException, IOException, ParseException {
        Object ob = new JSONParser().parse(new FileReader("src/assets/data/users_data.json"));

        JSONObject js = (JSONObject) ob;
        JSONArray jsonUsers = (JSONArray) js.get("users");
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < jsonUsers.size(); i++) {
            User user = mapper.readValue(jsonUsers.get(i).toString(), User.class);
            users.add(user);
        }

        Scanner sc = new Scanner(System.in);

        String uid;
        String upw;

        System.out.print("Input your ID: ");
        uid = sc.next();
        System.out.print("Input your Password: ");
        upw = sc.next();

        for (User user : users) {
            if (user.getId().equals(uid)) {
                if (user.getPassword().equals(upw)) {
                    System.out.println(user.getDisplayName() + "님 환영합니다.");
                    sc.close();
                    return true;
                } else {
                    System.out.print("PASSWORD가 일치하지 않습니다.");
                }
            } else {
                System.out.print("ID가 일치하지 않습니다.");
            }
        }
        sc.close();

        return false;
    }

    
}
