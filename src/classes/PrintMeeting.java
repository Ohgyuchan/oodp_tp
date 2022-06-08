package classes;

import java.util.ArrayList;

public	abstract class PrintMeeting {
	
	public ArrayList<Meeting> pmeet = new ArrayList<Meeting>();
	
	public PrintMeeting(ArrayList<Meeting> pmeet) {
		super();
		this.pmeet = pmeet;
	}



	abstract public void print(); 
}
