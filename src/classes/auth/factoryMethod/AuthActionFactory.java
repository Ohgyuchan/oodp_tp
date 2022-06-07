package classes.auth.factoryMethod;

import classes.auth.strategy.AuthAction;

public abstract class AuthActionFactory {
    public AuthAction create(String name) {
        return createAuthAction(name);
    }

    public abstract AuthAction createAuthAction(String name);

}
