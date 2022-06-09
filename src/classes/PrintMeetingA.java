package classes;

import java.util.ArrayList;

public class PrintMeetingA extends PrintMeeting {

    public PrintMeetingA(ArrayList<Meeting> pmeet) {
        super(pmeet);
    }

    public void print() {
        int i = 0;
        for (Meeting m : pmeet) {
            System.out.print("#" + i + " : ");
            System.out.println(m.getTitle());
            i++;
        }
    }
}
