package classes.auth;

import classes.auth.strategy.Auth;

public abstract class AuthFactory {
    public Auth create(String name) {
        return createAuth(name);
    }

    public abstract Auth createAuth(String name);

}
