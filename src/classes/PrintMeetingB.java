package classes;

import java.util.ArrayList;

public class PrintMeetingB extends PrintMeeting{

	public ArrayList<Meeting> pmeet = new ArrayList<Meeting>();
	
	public PrintMeetingB(ArrayList<Meeting> pmeet) {
		super(pmeet);
		this.pmeet = pmeet;
	}

	public void print() {
    	int i=0;
        for (Meeting m : pmeet) {
            System.out.print("@ "+i+" : ");
        	System.out.println(m.toString());
        	i++;
        }
	}

}
