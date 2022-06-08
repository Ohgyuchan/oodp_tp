package classes;

import java.util.ArrayList;
import java.util.List;

public class MeetingMemento {
	ArrayList mementoMeet;

	MeetingMemento() { // 메멘토 패턴, 접근자 default
		this.mementoMeet = new ArrayList();
	}

	void add(Meeting meet) {
		mementoMeet.add(meet);
	}
	
	List getSavedMeet() {
		return (List)mementoMeet.clone();
	}
}
