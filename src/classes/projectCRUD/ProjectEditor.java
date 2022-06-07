package classes.projectCRUD;

public class ProjectEditor {
    private ProjectEditStrategy strategy;

    public ProjectEditor() {
    }

    public void setProjectStrategy(ProjectEditStrategy strategy) {
        this.strategy = strategy;
    }

    public void run() {
        strategy.run();
    }
}
