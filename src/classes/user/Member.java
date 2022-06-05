package classes.user;

import java.util.Scanner;

public class Member extends Leader{
    
    public Member() {
    }

    public Member(Scanner sc) {
        super(sc);
    }

    public Member(String id, String displayName) {
        super(id, displayName);
    }
}
