package classes.projectCRUD;

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

    }

}
