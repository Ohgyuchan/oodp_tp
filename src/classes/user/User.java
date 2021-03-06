package classes.user;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Project;
import classes.singleton.SingletonJSON;

public class User extends Member {
    private String password;
    private ArrayList<String> projectIds;

    public User() {
    }

    public User(Scanner sc) {
        super(sc);
        System.out.print("Input Password: ");
        this.password = sc.nextLine();
        this.projectIds = new ArrayList<>();
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
        for (Project project : SingletonJSON.getInstance().getProjects(this.projectIds)) {
            i++;
            System.out.println("#" + (i) + ": " + project.getProjectName());
        }
    }

    public MementoProject createMemento() { // 메멘토패턴, 프로젝트 리스트 저장
        System.out.println("======= Save the projects by Memento =======");
        MementoProject m = new MementoProject();
        for (String string : projectIds) {
            m.projectAdd(string);
        }
        return m;
    }

    public void restoreFromMemento(MementoProject memento) { // 메멘토패턴, 프로젝트 리스트 불러오기
        this.projectIds.clear(); 
        for (String string : memento.getProjectIds()) {
            projectIds.add(string);
        }
        System.out.println("Restore the Projects");
    }

    @Override
    public void print() {
        super.print();
        System.out.println("PASSWORD:" + this.getPassword());
    }
}
