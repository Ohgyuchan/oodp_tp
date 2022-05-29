package classes.auth.strategy;

import java.util.ArrayList;
import java.util.Scanner;

import classes.User;
import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;

public class SignInAction implements Auth {
    @Override
    public boolean authAction() {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        ArrayList<User> users = new ArrayList<>();
        users = SingletonJSON.getInstance().getUserList();

        String uid;
        String upw;

        System.out.print("Input your ID: ");
        uid = sc.next();
        System.out.print("Input your Password: ");
        upw = sc.next();

        for (User user : users) {
            if (user.getId().equals(uid)) {
                if (user.getPassword().equals(upw)) {
                    SingletonAuth.getInstance().setCurrentUser(user);
                    System.out.println(SingletonAuth.getInstance().getCurrentUser().getDisplayName() + " Login Success");
                    return true;
                }
            }
        }
        return false;
    }
}
