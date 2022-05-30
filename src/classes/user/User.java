package classes.user;

import java.util.ArrayList;

import classes.MementoProject;
import classes.Project;
import classes.singleton.SingletonJSON;

public class User extends Member {
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
            System.out.println("#" + (i) + ": " + project.getProjectName());
        }
    }

    public MementoProject savetoMemento() { // 메멘토패턴, 프로젝트 리스트 저장
        System.out.println("save the projects");
        return new MementoProject(this.projectIds);
    }

    public void restoreFromMemento(MementoProject memento) { // 메멘토패턴, 프로젝트 리스트 불러오기
        this.projectIds = memento.getProjectIds();
    }
}
