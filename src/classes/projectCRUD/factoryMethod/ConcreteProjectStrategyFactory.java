package classes.projectCRUD.factoryMethod;

import classes.projectCRUD.strategy.ProjectCreateStrategy;
import classes.projectCRUD.strategy.ProjectDeleteStrategy;
import classes.projectCRUD.strategy.ProjectEditStrategy;
import classes.projectCRUD.strategy.ProjectUpdateStrategy;

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
