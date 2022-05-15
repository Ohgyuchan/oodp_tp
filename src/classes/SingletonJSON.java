package classes;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SingletonJSON {
    private static SingletonJSON instance = new SingletonJSON();
    private JSONObject usersJson;
    private JSONObject projectsJson;
    private JSONArray usersJsonArray;
    private JSONArray projectsJsonArray;

    private SingletonJSON() {
        this.setUsersJson();
    }

    public static SingletonJSON getInstance() {
        return instance;
    }

    private void setUsersJson() {
        Object usersData;
        Object projectsData;
        try {
            usersData = new JSONParser().parse(new FileReader("src/assets/data/users_data.json"));
            this.usersJson = (JSONObject) usersData;
            this.usersJsonArray = (JSONArray) this.usersJson.get("users");

            projectsData = new JSONParser().parse(new FileReader("src/assets/data/projects_data.json"));
            this.projectsJson = (JSONObject) projectsData;
            this.projectsJsonArray = (JSONArray) this.projectsJson.get("projects");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getUsersJson() {
        return this.usersJson;
    }

    public JSONObject getProjectsJson() {
        return this.projectsJson;
    }

    public JSONArray getUsersJsonArray() {
        return this.usersJsonArray;
    }

    public JSONArray getPrJsonArray() {
        return this.projectsJsonArray;
    }

    public Project getProjectFromJson() {
        return new Project();
    }

    public ArrayList<Project> getProjectsFromJson(ArrayList<String> projectIds) {
        ArrayList<Project> projects = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String projectId : projectIds) {
            try {
                projects.add(mapper.readValue(projectsJson.get(projectId).toString(), Project.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return projects;
    }

    public User getUserFromJSON() {
        return new User();
    }

}
