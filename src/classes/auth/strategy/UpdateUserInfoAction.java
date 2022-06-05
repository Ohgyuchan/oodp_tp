package classes.auth.strategy;

import java.util.Scanner;

import classes.singleton.SingletonAuth;
import classes.singleton.SingletonScanner;
import classes.user.User;

public class UpdateUserInfoAction implements Auth {

    @Override
    public boolean authAction() {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        User currentUser = SingletonAuth.getInstance().getCurrentUser();
        printInfoUpdateMenu();
        int select = sc.nextInt();
        if(select == 0) return false;
        if(select == 1) currentUser.print();
        if(select == 2) return updateDiplayName(sc, currentUser);
        if(select == 3) return updatePassword(sc, currentUser);
        return true;
    }

    private boolean updateDiplayName(Scanner sc, User currentUser) {
        System.out.println("INPUT NAME");
        currentUser.setDisplayName(sc.nextLine());
        return true;
    }
    private boolean updatePassword(Scanner sc, User currentUser) {
        System.out.println("INPUT PASSWORD");
        currentUser.setPassword(sc.nextLine());
        return true;
    }

    private void printInfoUpdateMenu() {
        System.out.println(" =================== ");
        System.out.println("0: CANCEL");
        System.out.println("1: PRINT USER INFO");
        System.out.println("2: UPDATE NAME");
        System.out.println("3: UPDATE PASSWORD");
        System.out.println(" =================== ");
    }
}
