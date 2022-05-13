package classes;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Project {
    private String projectId;
    private String projectName;
    private ArrayList<Member> members;
    private ArrayList<MainTask> tasks;
    private Leader leader;

    public Project() {

    }
    
    public Project(Scanner sc, User currentUser) {
        this.projectId = UUID.randomUUID().toString();
        this.projectName = sc.next();
        this.members.add((Member) currentUser);
        this.leader = (Leader) currentUser;
    }

    public Project(String projectName, ArrayList<Member> members, Leader leader) {
        this.projectName = projectName;
        this.members = members;
        this.leader = leader;
    }

    public ArrayList<MainTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<MainTask> tasks) {
        this.tasks = tasks;
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

    public ArrayList<Member> getMembers() {
        return members;
    }

    public int getMembersCount() {
        return members.size();
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public void print() {
        System.out.println("projectId: " + projectId);
        System.out.println("projectName: " + projectName);
        System.out.print("members: ");
        for (Member member : members) {
            System.out.println(member.getDisplayName());
        }
        System.out.println("leader" + leader.getDisplayName());
    }

    public void menu(Scanner sc) {
        print();
        System.out.println("0: Exit");
        System.out.println("1: Invite a member");
        System.out.println("2: Delete a member");
    }
}
