package classes;

// State Pattern
public class Complete implements TaskState {
    public String stateChange() {
        return "완료";
    }
}
