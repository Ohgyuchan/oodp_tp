package classes;

import java.util.ArrayList;

public class MainTask extends Task{
    private ArrayList<SubTask> subTasks = new ArrayList<SubTask>();
//    private ArrayList<Meeting> meetings;

    public MainTask(String title, State state) {
        super(title, state);
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

    public void addMetting() {

    }
}
