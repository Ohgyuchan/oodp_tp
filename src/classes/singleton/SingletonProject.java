package classes.singleton;

import classes.Project;

public class SingletonProject {
    private static SingletonProject instance = null;
    private Project currentProject;

    private SingletonProject() {
    }

    public static SingletonProject getInstance() {
        if (instance == null) {
            instance = new SingletonProject();
        }
        return instance;
    }

    public void setCurrentProject(Project project) {
        this.currentProject = project;
    }

    public Project getCurrentProject() {
        return this.currentProject;
    }
}
