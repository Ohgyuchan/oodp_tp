package classes;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

    public static Map<String, Object> getMapFromJsonObject(JSONObject jsonObject) {
        Map<String, Object> map = null;
        try {
            map = new ObjectMapper().readValue(jsonObject.toJSONString(), Map.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static ArrayList<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray) {

        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if (jsonArray != null) {
            int jsonSize = jsonArray.size();
            
            for (int i = 0; i < jsonSize; i++) {
                Map<String, Object> map = getMapFromJsonObject((JSONObject) jsonArray.get(i));
                list.add(map);
            }
        }
        return list;
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

    public JSONArray getProjectsJsonArray() {
        return this.projectsJsonArray;
    }

    public Project getProjectFromJson(String projectId) {
        return new Project();
    }

    public ArrayList<Project> getProjectsFromJson(ArrayList<String> projectIds) {
        ArrayList<Project> projects = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String projectId : projectIds) {
            try {
                for (Object projectJS : projectsJsonArray) {
                    // System.out.println(projectJS);
                    JSONObject pjso = (JSONObject) projectJS;
                    if (pjso.get("projectId").equals(projectId)) {
                        projects.add(mapper.readValue(pjso.toString(), Project.class));
                        // System.out.println(pjso.get("projectId").toString());
                    }
                }
                // if(mapper.readValue(projectsJson.get("projectId").toString(),
                // Project.class).getProjectId().equals(projectId)) {
                // projects.add(mapper.readValue(projectsJson.get("projectId").toString(),
                // Project.class));
                // }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return projects;
    }

    public User getUserFromJSON() {
        return new User();
    }

}
