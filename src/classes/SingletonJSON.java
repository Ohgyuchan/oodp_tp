package classes;

import org.json.simple.JSONObject;

public class SingletonJSON {
    private static SingletonJSON instance = new SingletonJSON ();


    private SingletonJSON() {

    }

    public static JSONObject getInstance() {
            return instance;
    }
}
