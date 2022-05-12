package classes;

public class Leader {
    private String id;
    private String displayName;

    public Leader() {

    }


    public Leader(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
    

    public String getid() {
        return this.id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
