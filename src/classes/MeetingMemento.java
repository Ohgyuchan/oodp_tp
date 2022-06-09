package classes;

import java.util.ArrayList;

public class MeetingMemento {
	private ArrayList<Meeting> mementoMeet;

	MeetingMemento() { // 메멘토 패턴, 접근자 default
		this.mementoMeet = new ArrayList<>();
	}

	void meetingAdd(Meeting meeting) {
		mementoMeet.add(meeting);
	}

	public ArrayList<Meeting> getSavedMeet() {
		return this.mementoMeet;
	}
}
