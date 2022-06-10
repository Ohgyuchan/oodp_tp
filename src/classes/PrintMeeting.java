package classes;

import java.util.ArrayList;

public abstract class PrintMeeting {
    public ArrayList<Meeting> pmeet = new ArrayList<>();

    public PrintMeeting(ArrayList<Meeting> pmeet) {
        this.pmeet = pmeet;
    }

    public void printAll() {
		countMeeting();
		print();
	}

	private void countMeeting() {
		System.out.println(pmeet.size() + " meetings");
	}

    abstract public void print();
}
