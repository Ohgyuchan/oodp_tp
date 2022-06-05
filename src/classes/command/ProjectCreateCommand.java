package classes.command;

import classes.Project;

public class ProjectCreateCommand implements ProjectCommand{
    private Project project;

    ProjectCreateCommand(Project project ) {
        this.project = project;
    }

    @Override
    public void run() {
        
    }
    
}
