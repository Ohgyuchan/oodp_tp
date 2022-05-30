package classes.singleton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import classes.Project;
import classes.user.User;

public class SingletonJSON {
    private static SingletonJSON instance = null;
    private JSONObject usersJson;
    private JSONObject projectsJson;
    private JSONArray usersJsonArray;
    private JSONArray projectsJsonArray;

    private SingletonJSON() {
        System.out.println("SingletonJSON constructed");
        this.setJson();
    }

    public static SingletonJSON getInstance() {
        if (instance == null) {
            instance = new SingletonJSON();
        }
        return instance;
    }

    private void setJson() {
        Object usersData;
        Object projectsData;
        try {
            usersData = new JSONParser().parse(new FileReader("assets/data/users_data.json"));
            this.usersJson = (JSONObject) usersData;
            this.usersJsonArray = (JSONArray) this.usersJson.get("users");

            projectsData = new JSONParser().parse(new FileReader("assets/data/projects_data.json"));
            this.projectsJson = (JSONObject) projectsData;
            this.projectsJsonArray = (JSONArray) this.projectsJson.get("projects");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getProjectMapFromJsonObject(JSONObject jsonObject) {
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

    public ArrayList<Map<String, Object>> getProjectListMapFromJsonArray(JSONArray jsonArray) {

        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if (jsonArray != null) {
            int jsonSize = jsonArray.size();

            for (int i = 0; i < jsonSize; i++) {
                Map<String, Object> map = getProjectMapFromJsonObject((JSONObject) jsonArray.get(i));
                list.add(map);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getUserMapFromJsonObject(JSONObject jsonObject) {
        Map<String, Object> map = null;
        try {
            map = new ObjectMapper().readValue(jsonObject.toJSONString(), Map.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("MAP HAS NULL VALUE");
            System.out.println(map);
            e.printStackTrace();
        }
        return map;
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JSONArray JsonArray = usersJsonArray;
        if (usersJsonArray != null) {
            int jsonSize = JsonArray.size();

            for (int i = 0; i < jsonSize; i++) {
                Map<String, Object> map = getUserMapFromJsonObject((JSONObject) JsonArray.get(i));
                User user = mapper.convertValue(map, User.class);
                list.add(user);
            }
        }
        return list;
    }

    public Project getProject(String projectId) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (Map<String, Object> projectMap : getProjectListMapFromJsonArray(projectsJsonArray)) {
                if (projectMap.get("projectId").equals(projectId)) {
                    return mapper.convertValue(projectMap, Project.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Project();
    }

    public ArrayList<Project> getProjects(ArrayList<String> projectIds) {
        ArrayList<Project> projects = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String projectId : projectIds) {
            try {
                for (Map<String, Object> projectMap : getProjectListMapFromJsonArray(projectsJsonArray)) {
                    if (projectMap.get("projectId").equals(projectId)) {
                        projects.add(mapper.convertValue(projectMap, Project.class));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return projects;
    }

    public User getUserFromUserId(String userId) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (Map<String, Object> userMap : getProjectListMapFromJsonArray(usersJsonArray)) {
                if (userMap.get("userId").equals(userId)) {
                    return mapper.convertValue(userMap, User.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
    }

    public ArrayList<User> getUsersFromUserIds(ArrayList<String> userIds) {
        ArrayList<User> users = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String userId : userIds) {
            try {
                for (Map<String, Object> userMap : getProjectListMapFromJsonArray(usersJsonArray)) {
                    if (userMap.get("userId").equals(userId)) {
                        users.add(mapper.convertValue(userMap, User.class));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @SuppressWarnings("unchecked")
    public void saveJson(User user) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        String userJsonInString = mapper.writeValueAsString(user);

        JSONObject userJson = (JSONObject) parser.parse(userJsonInString);
        usersJsonArray.add(userJson);
        for (Object object : usersJsonArray) {
            JSONObject jo = (JSONObject) object;
            if (userJson.get("id").toString().equals(jo.get("id").toString())) {
                if (!jo.equals(userJson)) {
                    usersJsonArray.remove(jo);
                }
            }
        }

        String usersJsonString = usersJson.toString();
        File usersJsonFile = new File("src/assets/data/users_data.json");
        writeStringToFile(usersJsonString, usersJsonFile);
    }

    @SuppressWarnings("unchecked")
    public void saveJson(Project project) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();

        String projectJsonInString = mapper.writeValueAsString(project);

        JSONObject projectJson = (JSONObject) parser.parse(projectJsonInString);

        projectsJsonArray.add(projectJson);

        String projectsJsonString = projectsJson.toString();

        File projectsJsonFile = new File("src/assets/data/projects_data.json");

        writeStringToFile(projectsJsonString, projectsJsonFile);

    }

    public void saveJson(Project project, User user) throws IOException, ParseException {
        saveJson(project);
        saveJson(user);
    }

    private void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }

    public void invite(Scanner sc, Project project) throws JsonMappingException, JsonProcessingException {
        ArrayList<User> users = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < usersJsonArray.size(); i++) {
            User user = mapper.readValue(usersJsonArray.get(i).toString(), User.class);
            if (project.getMemberIds().contains(user.getId()))
                continue;
            users.add(user);
        }

        for (int i = 0; i < users.size(); i++) {
            System.out.println("#" + i + " " + users.get(i).getId());
        }

        int index = sc.nextInt();
        project.addMemberId(users.get(index).getId());
        users.get(index).addProjectIds(project.getProjectId());
        try {
            saveJson(project, users.get(index));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void addUser() {
        // TODO: JSON Update
    }

    public void addProject() {
        // TODO: JSON Update
    }

    public void updateProject() {
        // TODO: JSON Update
    }
}
