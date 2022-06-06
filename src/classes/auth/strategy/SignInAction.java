package classes.auth.strategy;

import java.util.ArrayList;
import java.util.Scanner;

import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;
import classes.user.User;

public class SignInAction implements Auth {
    private static Auth instance = null;

    public static Auth getInstance() {
        if (instance == null) {
            instance = new SignInAction();
        }
        return instance;
    }

    @Override
    public boolean authAction() {
        System.out.println("========= SIGN IN =========");
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
                    System.out
                            .println(SingletonAuth.getInstance().getCurrentUser().getDisplayName() + " Login Success");
                    return true;
                }
            }
        }
        System.out.println("===== WRONG INFO ====");
        return false;
    }
}
