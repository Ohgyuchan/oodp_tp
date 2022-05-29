package classes;

public class Subtask implements Subtodo {
    private int num;
    private String title;
    private String state;

    public Subtask() {

    }

    public Subtask(String title) {
        this.title = title;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Obeserver Pattern
    @Override
    public void update(String msg) {
        this.setState(msg);
    }

    @Override
    public String toString() {
        return "\n" + this.getNum() + ".Subtask : " + this.getTitle() + " | 상태 : " + this.getState();
    }
}
