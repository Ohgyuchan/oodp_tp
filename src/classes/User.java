package classes;

import java.util.ArrayList;

public class User extends Member{
    private String password;
    private ArrayList<Project> projects;
    
    public User() {
    }
    
    public User(String id, String password, String displayName, ArrayList<Project> projects) {
        super(id, displayName);
        this.password = password;
        this.projects = projects;
    }

    public ArrayList<Project> getprojects() {
        return projects;
    }

    public void setprojects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void printProjects() {
        for(int i = 0; i < projects.size(); i++) {
            System.out.println("#"+ (i+1) + ": " + projects.get(i).getProjectName());
        }
    }
}
