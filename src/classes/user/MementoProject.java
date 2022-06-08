package classes.user;

import java.util.ArrayList;
import java.util.List;

public class MementoProject {
	ArrayList projectIds;

	MementoProject(){
		this.projectIds = new ArrayList();
	}
	
	void projectAdd(String ids) {
		projectIds.add(ids);
	}
	
	List getSavedProject() {
		return (List)projectIds.clone(); 
	}
}
