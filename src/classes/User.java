package classes;

import java.util.ArrayList;

public class User extends Member {
    private ArrayList<Project> projects;
    
    public User() {
    }
    
    public User(String id, String displayName, ArrayList<Project> projects) {
        super(id, displayName);
        this.projects = projects;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
