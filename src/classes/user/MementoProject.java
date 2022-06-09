package classes.user;

import java.util.ArrayList;

public class MementoProject {
	private ArrayList<String> projectIds;

	MementoProject() {
		this.projectIds = new ArrayList<>();
	}

	MementoProject(ArrayList<String> saveIds) { // 메멘토 패턴, 접근자 default
		this.projectIds = saveIds;
	}

	void projectAdd(String ids) {
		projectIds.add(ids);
	}

	public ArrayList<String> getProjectIds() {
		return projectIds;
	}

	void setProjectIds(ArrayList<String> projectIds) {
		this.projectIds = projectIds;
	}
}
