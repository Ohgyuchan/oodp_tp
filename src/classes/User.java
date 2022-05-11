package classes;

import java.util.ArrayList;

public class User extends Member {
    private ArrayList<Project> projects;

    public User() {
    }

    public User(String uid, String email, String displayName, String photoUrl, ArrayList<Project> projects) {
        super(uid, email, displayName, photoUrl);
        this.projects = projects;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
