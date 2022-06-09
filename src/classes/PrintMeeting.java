package classes;

import java.util.ArrayList;

public	abstract class PrintMeeting {
	
	public ArrayList<Meeting> pmeet = new ArrayList<Meeting>();
	
	public PrintMeeting(ArrayList<Meeting> pmeet) {
		super();
		this.pmeet = pmeet;
	}

	public void countMeeting() {
		System.out.println(pmeet.size()+" meetings");
	}

	abstract public void print(); 
}
