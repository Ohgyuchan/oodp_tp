package classes.projectCRUD.factoryMethod;

import classes.projectCRUD.strategy.ProjectEditStrategy;

public abstract class ProjectStrategyFactory {
    public ProjectEditStrategy create(String name) {
        return createProjectStrategy(name);
    }

    public abstract ProjectEditStrategy createProjectStrategy(String name);
}
