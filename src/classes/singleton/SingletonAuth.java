package classes.singleton;

import classes.user.User;

public class SingletonAuth {
    private static SingletonAuth instance = null;
    private User currentUser;

    private SingletonAuth() {
    }

    public static SingletonAuth getInstance() {
        if (instance == null) {
            instance = new SingletonAuth();
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
