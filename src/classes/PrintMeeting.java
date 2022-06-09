package classes;

import java.util.ArrayList;

public abstract class PrintMeeting {
    public ArrayList<Meeting> pmeet = new ArrayList<>();

    public PrintMeeting(ArrayList<Meeting> pmeet) {
        this.pmeet = pmeet;
    }

    abstract public void print();
}
