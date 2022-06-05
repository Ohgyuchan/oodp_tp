package classes.auth.strategy;

import java.util.Scanner;

import classes.singleton.SingletonAuth;
import classes.singleton.SingletonScanner;
import classes.user.User;

public class InfoUpdateAction implements Auth {

    @Override
    public boolean authAction() {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        User currentUser = SingletonAuth.getInstance().getCurrentUser();
        printInfoUpdateMenu();
        int select = sc.nextInt();
        if(select == 0) return false;
        if(select == 1) return updateDiplayName(sc, currentUser);
        if(select == 2) return updatePassword(sc, currentUser);
        return true;
    }

    private boolean updateDiplayName(Scanner sc, User currentUser) {
        return false;
    }
    private boolean updatePassword(Scanner sc, User currentUser) {
        return false;
    }

    private void printInfoUpdateMenu() {
        System.out.println(" =================== ");
        System.out.println("0: CANCEL");
        System.out.println("1: UPDATE NAME");
        System.out.println("2: UPDATE PASSWORD");
        System.out.println(" =================== ");
    }
}
