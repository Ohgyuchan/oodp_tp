package classes;

public class SubTask implements Observer {
    private int num;
    private String title;
    private String state;

    public SubTask() {

    }

    public SubTask(String title) {
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
