package classes;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Project {
    private String projectId;
    private String projectName;
    private ArrayList<String> memberIds = new ArrayList<>();
    private ArrayList<MainTask> tasks = new ArrayList<MainTask>();
    private String leaderId;

    public Project() {

    }

    public Project(Scanner sc, User currentUser) {
        this.projectId = UUID.randomUUID().toString();
        this.projectName = sc.next();
        this.memberIds.add(currentUser.getId());
        this.leaderId = currentUser.getId();
    }

    public Project(String projectName, ArrayList<String> memberIds, String leaderId) {
        this.projectId = UUID.randomUUID().toString();
        this.projectName = projectName;
        this.memberIds = memberIds;
        this.leaderId = leaderId;
    }

    public ArrayList<MainTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<MainTask> tasks) {
        this.tasks = tasks;
    }

    public void addTask(MainTask task) {
        tasks.add(task);
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<String> getMemberIds() {
        return memberIds;
    }

    public void addMemberId(String memberId) {
        this.memberIds.add(memberId);
    }

    // public int getMembersCount() {
    //     return memberIds.size();
    // }

    public void setMemberIds(ArrayList<String> memberIds) {
        this.memberIds = memberIds;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public void print() {
        System.out.println("projectId: " + projectId);
        System.out.println("projectName: " + projectName);
        System.out.print("memberIds: ");
        for (String memberId : memberIds) {
            System.out.println(memberId);
        }
        System.out.println("leaderId: " + leaderId);
    }

    public void menu(Scanner sc) {
        print();
        System.out.println("0: Exit");
        System.out.println("1: List tasks");
        System.out.println("2: Manage tasks");
        System.out.println("3: Manage Members");
    }
}
