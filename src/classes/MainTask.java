package classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import classes.singleton.SingletonScanner;

public class MainTask extends AbstractTask implements Todo {
    private int num;
    private String title;
    private String backgroundColor = "White";
    private ArrayList<SubTask> subTasks = new ArrayList<>(); // Observer Pattern
    private ArrayList<Observer> observers = new ArrayList<>();

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

    public void setSubTask(String title, String person, int num) {
        SubTask st = new SubTask();
        st.setTitle(title);
        st.setPerson(person);
        st.setNum(num);
        st.setState("대기중");
        this.subscribe(st); // Observer Pattern
        this.subTasks.add(st);
        this.subTasks.sort(Comparator.comparing(SubTask::getNum));
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
        setObservers(observers);
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers.addAll(subTasks);
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

    public void deleteMeeting(Scanner sc) {
        int index;
        meetingList();
        System.out.println("Please Enter the number of index to delete :");
        index = sc.nextInt();
        meetings.remove(index);
    }

    public MeetingMemento createMemento() {
        System.out.println("====saved meeting list====");
        MeetingMemento m = new MeetingMemento();
        for (Meeting meeting : meetings) {
            m.meetingAdd(meeting);
        }
        return m;
    }

    public void restoreMeeting(MeetingMemento mt) {
        this.meetings.clear();
        for (Meeting meeting : mt.getSavedMeet()) {
            meetings.add(meeting);
        }
        System.out.println("Restore the Meetings");
    }

    public void meetingList() {
        System.out.println("1: ONLY TITLE");
        System.out.println("2: TITLE & TIME");
        int input = SingletonScanner.getInstance().getScanner().nextInt();
        PrintMeeting pm;
        if (input == 1) {
            pm = new PrintMeetingA(meetings);
        } else {
            pm = new PrintMeetingB(meetings);
        }
        pm.printAll();
    }

    // Observer Pattern
    @Override
    public void subscribe(Observer subTodo) {
        observers.add(subTodo);
    }

    // Observer Pattern
    @Override
    public void unsubscribe(Observer subtodo) {
        observers.remove(subtodo);
    }

    // Observer Pattern
    @Override
    public void notifySubTodo(String msg) {
        observers.forEach(observer -> observer.update(msg));
    }

    public String toString() {
        return "[" + this.getNum() + "] Task : " + this.getTitle() + " | 상태 : " + this.getState()
                + " | 배경색 : " + this.getBackgroundColor() + "\nSubtask: " + this.subTasks + "\n" + "Meetings: "
                + this.getMeetings() + "\n";
    }

    public void turnOn() {
        System.out.print(toString());
    }
}