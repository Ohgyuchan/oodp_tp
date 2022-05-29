package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainTask extends Task {
    private ArrayList<SubTask> subTasks = new ArrayList<SubTask>();
    private ArrayList<Meeting> meetings;

    public MainTask() {

    }

    public MainTask(String title, State state) {
        super(title, state);
        this.subTasks = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public void edit() {
        super.edit();
    }

    public void addSubTask() {

    }

    public void addMeeting(Scanner sc) {
        System.out.println("Meeting title : ");
        String title = sc.next();
        System.out.println("Meeting Start Time : ");
        String time = sc.next();

        Meeting nmeet = new Meeting(time, title);
        meetings.add(nmeet);
    }
    
    public void dateList() {
        Collections.sort(meetings);
        for (Meeting m : meetings) {
            System.out.println(m.toString());
        }
    }

    public void editMeeting() {

    }

}