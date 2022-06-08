package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MainTask extends AbstractTask implements Todo {
    private int num;
    private String title;
    private String backgroundColor = "White";
    private ArrayList<SubTask> subTasks = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>(); // Observer Pattern

    private String state = "대기중";

    private ArrayList<Meeting> meetings = new ArrayList<>();

    public ArrayList<Meeting> getMeetings() {
        return this.meetings;
    }

    public void setMeetings(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }

    public MainTask() {

    }

    public MainTask(String title) {
        this.title = title;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(String title, String person, int num) {
        SubTask st = new SubTask();
        st.setTitle(title);
        st.setPerson(person);
        st.setNum(num);
        st.setState("대기중");
        this.subscribe(st); // Observer Pattern
        this.subTasks.add(st);
        this.subTasks.sort(Comparator.comparing(SubTask::getNum));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    // Observer Patter
    public void upgradeComplete() {
        this.backgroundColor = "Blue";
        this.state = "완료";
        notifySubTodo("완료");
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

    // Observer Patter
    @Override
    public void subscribe(Observer subtodo) {
        observers.add(subtodo);
    }

    // Observer Patter
    @Override
    public void unsubscribe(Observer subtodo) {
        observers.remove(subtodo);
    }

    // Observer Patter
    @Override
    public void notifySubTodo(String msg) {
        observers.forEach(crew -> crew.update(msg));
    }

    public String toString() {
        return "[" + this.getNum() + "] Task : " + this.getTitle() + " | 상태 : " + this.getState()
                + " | 배경색 : " + this.getBackgroundColor() + "\nSubtask : " + this.getSubTasks() + "\n" + "Mettings: "
                + this.getMeetings();
    }

    public void turnOn() {
        System.out.print("[" + this.getNum() + "] Task : " + this.getTitle() + " | 상태 : " + this.getState()
        + " | 배경색 : " + this.getBackgroundColor() + "\nSubtask : " + this.getSubTasks() + "\n" + "Mettings: "
        + this.getMeetings() + "\n");
    }
}