package classes.command;

import classes.Project;

public class ProjectDeleteCommand implements ProjectCommand{
    private Project project;

    ProjectDeleteCommand(Project project) {
        this.project = project;
    }
    
    @Override
    public void run() {
        
    }
    
}
