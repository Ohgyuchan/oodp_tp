package classes;

import java.util.ArrayList;

public class MeetingMemento {
    ArrayList<Meeting> mementoMeet;

	public MeetingMemento() { // 메멘토 패턴, 접근자 default
		this.mementoMeet = new ArrayList<>();
	}

	void add(Meeting meet) {
		mementoMeet.add(meet);
	}
	
	ArrayList<Meeting> getSavedMeet() {
		return mementoMeet;
	}
}
