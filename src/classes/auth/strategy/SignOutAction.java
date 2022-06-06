package classes.auth.strategy;

import classes.singleton.SingletonAuth;

public class SignOutAction implements Auth {

    private SignOutAction() {
        
    }

    private static Auth instance = null;

    public static Auth getInstance() {
        if (instance == null) {
            instance = new SignOutAction();
        }
        return instance;
    }

    @Override
    public boolean authAction() {
        SingletonAuth.getInstance().setCurrentUser(null);
        if (SingletonAuth.getInstance().getCurrentUser() != null) {
            System.out.println("======= SignOut FAILED =======");
            return false;
        }

        else {
            System.out.println("========= SignOut =========");
            return true;
        }
    }
}
