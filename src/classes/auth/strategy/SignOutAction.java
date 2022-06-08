package classes.auth.strategy;

import classes.singleton.SingletonAuth;

public class SignOutAction implements Auth {
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
