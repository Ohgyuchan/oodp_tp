package classes;

import java.util.ArrayList;

public class MementoProject {
	private ArrayList<String> projectIds;
	
	public MementoProject(ArrayList<String> saveIds){
		projectIds=saveIds;
	}
	
	
	public ArrayList<String> getProjectIds() {
		return projectIds;
	}


	public void setProjectIds(ArrayList<String> projectIds) {
		this.projectIds = projectIds;
	}

	public ArrayList<String> getSavedState() {
		return projectIds;
	}
}
