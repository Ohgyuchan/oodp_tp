package classes;

import java.util.ArrayList;

public class MainTask extends Task{
    private ArrayList<SubTask> subTasks;
//    private ArrayList<Meeting> meetings;



    public MainTask() {
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
