package classes.auth.strategy;

import classes.singleton.SingletonAuth;

public class SignOutAction implements Auth {
    @Override
    public boolean authAction() {
        SingletonAuth.getInstance().setCurrentUser(null);
        System.out.println("====== SignOut ======");
        if (SingletonAuth.getInstance().getCurrentUser() != null)
            return false;
        else
            return true;
    }
}
