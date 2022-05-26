package classes.singleton;

import classes.User;

public class SingletonAuth {
    private static SingletonAuth instance;
    private User currentUser;

    private SingletonAuth() {
        System.out.println("SingletonAuth constructed");
    }
    
    public static SingletonAuth getInstance() {
        if(instance == null){
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
