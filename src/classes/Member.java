package classes;

public class Member {
    private String uid;
    private String email;
    private String displayName;
    private String photoUrl;

    public Member() {
    }

    public Member(String uid, String email, String displayName, String photoUrl) {
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.photoUrl = photoUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

//    public void setProjectsFromStringList(ArrayList<String> projects) {
//        ArrayList<Project> projectArrayList = new ArrayList<>();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        for(String project : projects){
//            db.collection("Projects").document(project).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                    if(documentSnapshot.exists()) {
//                        Map<String, Object> data = documentSnapshot.getData();
//                        Project kProject = new Project((String) data.get("projectName"), (ArrayList<String>) data.get("members"), (String) data.get("leader"));
//                        projectArrayList.add(kProject);
//                    }
//                }
//            });
//        }
////        setProjects(projectArrayList);
//    }
}
