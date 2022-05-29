package classes;

// State Pattern
public class Waiting implements TaskState {
    public String stateChange() {
        return "대기중";
    }
}
