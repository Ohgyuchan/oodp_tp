package classes.auth.strategy;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;

public class UpdateUserInfoAction implements AuthAction {
    private static AuthAction instance = null;

    private UpdateUserInfoAction() {
    }

    public static AuthAction getInstance() {
        if (instance == null) {
            instance = new UpdateUserInfoAction();
        }
        return instance;
    }

    @Override
    public boolean authAction() {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        printInfoUpdateMenu();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 0) {
            try {
                SingletonJSON.getInstance().saveJson(SingletonAuth.getInstance().getCurrentUser());
            } catch (ConcurrentModificationException | IOException | ParseException e) {
                e.printStackTrace();
            }
            return false;
        }
        if (select == 1)
            SingletonAuth.getInstance().getCurrentUser().print();
        if (select == 2)
            return updateDiplayName(sc);
        if (select == 3)
            return updatePassword(sc);
        return true;
    }

    private boolean updateDiplayName(Scanner sc) {
        System.out.print("INPUT NAME: ");
        String name = sc.nextLine();
        SingletonAuth.getInstance().getCurrentUser().setDisplayName(name);
        return true;
    }

    private boolean updatePassword(Scanner sc) {
        System.out.print("INPUT PASSWORD:  ");
        String password = sc.nextLine();
        SingletonAuth.getInstance().getCurrentUser().setPassword(password);
        return true;
    }

    private void printInfoUpdateMenu() {
        System.out.println(" =================== ");
        System.out.println("0: EXIT");
        System.out.println("1: PRINT USER INFO");
        System.out.println("2: UPDATE NAME");
        System.out.println("3: UPDATE PASSWORD");
        System.out.println(" =================== ");
    }
}
