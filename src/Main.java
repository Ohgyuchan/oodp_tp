import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {
        try {
            Object ob = new JSONParser().parse(new FileReader("src/assets/data/users_data.json"));

            JSONObject js = (JSONObject) ob;

            System.out.println(js.get("users"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    
    }

    private void init() {

    }
}
