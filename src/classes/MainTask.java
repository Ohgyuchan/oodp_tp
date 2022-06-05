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
    private ArrayList<Observer> subTodos = new ArrayList<>(); // Observer Pattern
    private Waiting waiting = new Waiting();
    private Complete complete = new Complete();
    private TaskState taskState = waiting; // State Pattern
    private String state = taskState.stateChange();
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

    // State Pattern
    public void setState(TaskState taskState) {
        this.state = taskState.stateChange();
    }

    public String getState() {
        return state;
    }

    // State Pattern
    public String stateChange() {
        return taskState.stateChange();
    }

    // Observer Patter
    public void upgradeComplete() {
        this.backgroundColor = "Blue";
        this.state = complete.stateChange();
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
        subTodos.add(subtodo);
    }

    // Observer Patter
    @Override
    public void unsubscribe(Observer subtodo) {
        subTodos.remove(subtodo);
    }

    // Observer Patter
    @Override
    public void notifySubTodo(String msg) {
        subTodos.forEach(crew -> crew.update(msg));
    }

    public String toString() {
        return "[" + this.getNum() + "] Task : " + this.getTitle() + " | 상태 : " + this.getState()
                + " | 배경색 : " + this.getBackgroundColor() + "\nSubtask : " + this.getSubTasks() + "\n" + "Mettings: " + this.getMeetings();
    }
}