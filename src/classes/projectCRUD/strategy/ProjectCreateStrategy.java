package classes.projectCRUD.strategy;

import classes.Facade;
import classes.singleton.SingletonAuth;
import classes.singleton.SingletonScanner;

public class ProjectCreateStrategy implements ProjectEditStrategy {
    private static ProjectEditStrategy instance = null;

    private ProjectCreateStrategy() {
    }

    public static ProjectEditStrategy getInstance() {
        if (instance == null) {
            instance = new ProjectCreateStrategy();
        }
        return instance;
    }

    @Override
    public void run() {
        Facade facade = new Facade();
        facade.createProject(SingletonScanner.getInstance().getScanner(), SingletonAuth.getInstance().getCurrentUser());
    }

}
