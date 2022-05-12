import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import classes.User;

public class Main {
    private static ArrayList<Object> users;

    public static void main(String[] args) {
        try {
            Object ob = new JSONParser().parse(new FileReader("src/assets/data/users_data.json"));

            JSONObject js = (JSONObject) ob;
            JSONArray jsonUsers = (JSONArray) js.get("users");
            ObjectMapper mapper = new ObjectMapper();
            
            for(int i = 0; i < jsonUsers.size(); i++){
                User user = mapper.readValue(jsonUsers.get(0).toString(), User.class);
                users.add(user);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    
    }
}
