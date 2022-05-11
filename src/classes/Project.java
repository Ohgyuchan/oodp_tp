package classes;

import java.util.ArrayList;

public class Project {
    private String projectId;
    private String projectName;
    private ArrayList<Member> members;
    private Leader leader;

    public Project() {

    }

    public Project(String projectId, String projectName, ArrayList<Member> members, Leader leader) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.members = members;
        this.leader = leader;
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

//    public void setLeaderFromString(String leader) {
//        ArrayList<Leader> leaderArrayList = new ArrayList<>();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Users").document(leader).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Map<String, Object> data = documentSnapshot.getData();
//                Leader kLeader = new Leader((String) data.get("uid"), (String) data.get("email"), (String) data.get("displayName"),(String) data.get("photoUrl"), (ArrayList<String>) data.get("projects"));
//                leaderArrayList.add(kLeader);
//            }
//        });
////        setLeader(leaderArrayList.get(0));
//    }

//    public ArrayList<Member> getMembersFromStringList(ArrayList<String> members) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        ArrayList<Member> memberArrayList = new ArrayList<>();
//        for(String member : members){
//            System.out.println(member);
//            db.collection("Users").document(member).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if(task.isSuccessful()) {
//                        DocumentSnapshot documentSnapshot = task.getResult();
//                        Map<String, Object> data = documentSnapshot.getData();
//                        Member kMember = new Member((String) data.get("uid"), (String) data.get("email"), (String) data.get("displayName"),(String) data.get("photoUrl"), (ArrayList<String>) data.get("projects"));
//                        memberArrayList.add(kMember);
//                    }
//                }
//            });
//        }
//        return memberArrayList;
//    }
}
