package classes.command;

public class ProjectEditor {
    private ProjectCommand command;

    ProjectEditor() {
        
    }

    ProjectEditor(ProjectCommand command) {
        this.command = command;
    }

    public void setProjectCommand(ProjectCommand command) {
        this.command = command;
    }

    public void edit() {
        command.run();
    }
}
