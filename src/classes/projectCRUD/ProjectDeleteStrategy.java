package classes.projectCRUD;

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

    }

}
