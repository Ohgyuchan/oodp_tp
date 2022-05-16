package classes;

import java.util.ArrayList;

public class User extends Member{
    private String password;
    private ArrayList<String> projectIds;
    
    public User() {
    }
    
    public User(String id, String password, String displayName, ArrayList<String> projectIds) {
        super(id, displayName);
        this.password = password;
        this.projectIds = projectIds;
    }

    public ArrayList<String> getProjectIds() {
        return projectIds;
    }

    public void setProject(ArrayList<String> projectIds) {
        this.projectIds = projectIds;
    }

    public void addProjectIds(String projectId) {
        this.projectIds.add(projectId);
    }

    public void deleteProject(int index) {
        this.projectIds.remove(index);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void printProjects() {
        int i = 0;
        for (Project project : SingletonJSON.getInstance().getProjects(projectIds)) {
            i++;
            System.out.println("#"+ (i) + ": " + project.getProjectName());
        }
        
    }
}
