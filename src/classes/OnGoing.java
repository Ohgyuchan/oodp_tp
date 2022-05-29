package classes;

// State Pattern
public class OnGoing implements TaskState {
    public String stateChange() {
        return "진행중";
    }
}
