import java.util.ArrayList;

import classes.Leader;
import classes.Member;
import classes.Project;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Member> members = new ArrayList<>();
        
        members.add(new Member("uid_00", "email_00", "displayName_00", "photoUrl_00"));
        members.add(new Member("uid_01", "email_01", "displayName_01", "photoUrl_01"));
        members.add(new Member("uid_03", "email_03", "displayName_03", "photoUrl_03"));
        
        Project pj = new Project("01","OODP", members, (Leader) members.get(0));
        
        System.out.println(pj.getProjectId());
        System.out.println(pj.getMembersCount());
        System.out.println(pj.getLeader().getDisplayName());
        for(int i = 0; i < pj.getMembersCount(); i++) {
            System.out.println(pj.getMembers().get(i).getDisplayName());
        }
    }
}
