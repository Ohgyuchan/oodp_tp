package classes.projectCRUD.factoryMethod;

import classes.projectCRUD.ProjectEditStrategy;
import classes.projectCRUD.ProjectCreateStrategy;
import classes.projectCRUD.ProjectDeleteStrategy;
import classes.projectCRUD.ProjectUpdateStrategy;

public class ConcreteProjectStrategyFactory extends ProjectStrategyFactory {

    @Override
    public ProjectEditStrategy createProjectStrategy(String name) {
        switch(name){
			case "c": return ProjectCreateStrategy.getInstance();
			case "u": return ProjectUpdateStrategy.getInstance();
			case "d": return ProjectDeleteStrategy.getInstance();
		}
        return null;
    }

}
