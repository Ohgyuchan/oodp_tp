package classes;

import java.util.ArrayList;
import java.util.Date;

public class Meeting implements Comparable<Meeting>{
    private String startTime;
    private String title;

	private String content;
    private String dir;
    private ArrayList<Comment> comments;

    public Meeting() {
    }

    public Meeting(String startTime, String title, String content, String dir, ArrayList<Comment> comments) {
        this.startTime = startTime;
        this.content = content;
        this.dir = dir;
        this.comments = comments;
        this.title = title;

    }

    public Meeting(String startTime, String title) {
        this.startTime = startTime;
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
    public int compareTo(Meeting m) {
    	return startTime.compareTo(m.startTime);
    }

	@Override
	public String toString() {
		return "Meeting [startTime=" + startTime + ", title=" + title + "]";
	}
    

}
