package classes.user;

import java.util.Scanner;

public class Leader {
    private String id;
    private String displayName;

    public Leader() {

    }

    public Leader(Scanner sc) {
        sc.nextLine();
        System.out.print("Input ID: ");
        this.id = sc.nextLine();
        System.out.print("Input display name: ");
        this.displayName = sc.nextLine();
    }

    public Leader(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void print() {
        System.out.println(this.getId());
    }

}
