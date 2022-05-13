package classes;

import java.util.ArrayList;

public class User extends Member{
    private String password;
    private ArrayList<String> projectIds;
    
    public User() {
    }

    public ArrayList<String> getprojectIds() {
        return projectIds;
    }

    public void setprojectIds(ArrayList<String> projectIds) {
        this.projectIds = projectIds;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
