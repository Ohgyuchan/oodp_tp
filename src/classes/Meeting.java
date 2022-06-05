package classes;

import java.util.ArrayList;
import java.util.Scanner;

import classes.user.User;

public class Meeting implements Comparable<Meeting> {
    private String startTime;
    private String title;

    private String content;
    private String dir;
    private ArrayList<Comment> comments;
    private MeetingLog Log = new MeetingLog();

    public Meeting() {
    }

    public Meeting(String startTime, String title, String content, String dir, ArrayList<Comment> comments) {
        this.startTime = startTime;
        this.content = content;
        this.dir = dir;
        this.comments = comments;
        this.title = title;

    }
    
    public void printMeeting(){
    	System.out.println("===========================");
    	System.out.println("0: EXIT");
        System.out.println("1: WRITE MEETING LOG");
        System.out.println("2: VIEW MEETING LOG");
        System.out.println("===========================");
    }
    
    public void printMeeting(Scanner sc, User user, Project project) {
    	
        boolean FLAG = true;
        while (FLAG) {
        	printMeeting();
            int TAG = sc.nextInt();
            switch (TAG) {
                case 0:
                    FLAG = false;
                    break;
                case 1:
                	write(user, project);
                    break;
                case 2:
                	read();
                	FacadeComment comment = new FacadeComment();
                	comment.writeComment(this.comments, sc, user, project);
                    break;
                default:
                    break;
            }
        }
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

    public void read() {
        IRead proxy = new Proxy();
        proxy.Load(Log.getFileName());
    }

    public void write(User user, Project project) {
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	
    	System.out.println("FileName: ");
    	String fileName = sc.next();
    	System.out.println("Write Down");
    	String text = sc.next();
    	
    	System.out.println("fileName");
        Log.WriteMeetingLog(text, fileName, user, project);
    }

}
