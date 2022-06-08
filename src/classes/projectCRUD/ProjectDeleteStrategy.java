package classes.projectCRUD;

import classes.singleton.SingletonAuth;
import classes.singleton.SingletonScanner;

public class ProjectDeleteStrategy implements ProjectEditStrategy {
    private static ProjectEditStrategy instance = null;

    private ProjectDeleteStrategy() {
    }

    public static ProjectEditStrategy getInstance() {
        if (instance == null) {
            instance = new ProjectDeleteStrategy();
        }
        return instance;
    }

    @Override
    public void run() {
        SingletonAuth.getInstance().getCurrentUser().printProjects();
        System.out.print("Please Enter the index to delete: ");
        int indexToDelete = SingletonScanner.getInstance().getScanner().nextInt();
        SingletonAuth.getInstance().getCurrentUser().deleteProject(indexToDelete - 1);
    }

}
