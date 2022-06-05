package classes;

import java.util.ArrayList;

public class MementoProject {
	private ArrayList<String> projectIds;

	public MementoProject(ArrayList<String> saveIds) { // 메멘토 패턴, 접근자 default
		projectIds = saveIds;
	}

	public ArrayList<String> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(ArrayList<String> projectIds) {
		this.projectIds = projectIds;
	}
}
