package classes;

import java.util.ArrayList;
import java.util.Date;

public class Meeting {
    private Date startTime;
    private String content;
    private String dir;
    private ArrayList<Comment> comments;

    public Meeting() {
    }

    public Meeting(Date startTime, String content, String dir, ArrayList<Comment> comments) {
        this.startTime = startTime;
        this.content = content;
        this.dir = dir;
        this.comments = comments;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
