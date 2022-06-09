package classes;

import java.util.ArrayList;
import java.util.Comparator;

public class SubTask implements Observer {
    private int num;
    private String title;
    private String state;
    private String person;
    private ArrayList<Comment> comments = new ArrayList<>();

    public SubTask() {

    }

    public SubTask(String title) {
        this.title = title;
    }

    public void setComments(String content, String writer, int num) {
        Comment cmt = new Comment();
        cmt.setContent(content);
        cmt.setWriter(writer);
        cmt.setNum(num);
        this.comments.add(cmt);
        this.comments.sort(Comparator.comparing(Comment::getNum));
    }

    public ArrayList<Comment> getComments() {
        return comments;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    // Obeserver Pattern
    @Override
    public void update(String msg) {
        this.state = msg;
    }

    @Override
    public String toString() {
        return "\n" + this.getNum() + ".Subtask : " + this.getTitle() + " | 상태 : " + this.getState() + " | 담당자 : "
                + this.getPerson();
    }
}
