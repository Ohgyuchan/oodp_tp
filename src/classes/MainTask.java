package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MainTask extends AbstractTask implements Todo {
    private int num ;
    private String title ;
    private String backgroundColor ;
    private ArrayList<Subtask> subtaskList ;
    private ArrayList<Subtodo> subtodos = new ArrayList<>() ; // Observer Pattern
    private TaskState taskState; // State Pattern
    private ArrayList<Meeting> meetings;

    public MainTask() {

    }

    public MainTask(String title) {
        this.title = title ;
        this.backgroundColor = "White" ;
        this.taskState = new Waiting(); // State Pattern
        this.subtaskList = new ArrayList<Subtask>();
    }

    public ArrayList<Subtask> getSubtask() {
        return subtaskList;
    }

    public void setSutbtask(String title, int num) {
        Subtask st = new Subtask() ;
        st.setTitle(title);
        st.setNum(num);
        st.setState("대기중");
        this.subscribe(st); // Observer Pattern
        this.subtaskList.add(st);
        this.subtaskList.sort(Comparator.comparing(Subtask::getNum));
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title ;
    }

    public int getNum() {
        return num ;
    }

    public void setNum(int num) {
        this.num = num ;
    }

    public String getBackgroundColor() {
        return backgroundColor ;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor ;
    }

    // State Pattern
    public void setTaskState(TaskState tastState) {
        this.taskState = new OnGoing() ;
    }

    // State Pattern
    public String stateChange() {
        return taskState.stateChange();
    }

    // Observer Patter
    public void upgradeComplete() {
        this.backgroundColor = "Blue" ;
        // this.state = "완료" ;
        this.taskState = new Complete();
        notifySubtodo("완료");
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
    public void subscribe(Subtodo subtodo) {
        subtodos.add(subtodo);
    }

    // Observer Patter
    @Override
    public void unsubscribe(Subtodo subtodo) {
        subtodos.remove(subtodo);
    }

    // Observer Patter
    @Override
    public void notifySubtodo(String msg) {
        subtodos.forEach(crew -> crew.update(msg));
    }

    @Override
    public String toString() {
        return "[" + this.getNum() + "] Task : " + this.getTitle() + " (상태 : " + this.taskState.stateChange() + "/배경색 : " + this.getBackgroundColor() + ")" + "\nSubtask : " + this.getSubtask() + "\n";
    }
}