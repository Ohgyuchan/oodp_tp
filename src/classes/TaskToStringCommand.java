package classes;

public class TaskToStringCommand implements Command {
    private MainTask theMainTask;
    public TaskToStringCommand(MainTask mainTask) { this.theMainTask = mainTask; }
    public void execute() { theMainTask.turnOn(); }
}