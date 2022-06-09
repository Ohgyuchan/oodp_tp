package classes;

import java.util.ArrayList;
import java.util.Scanner;

import classes.singleton.SingletonJSON;
import classes.user.User;
import java.io.IOException;

public class Meeting implements Comparable<Meeting> {
    private String startTime;
    private String title;
    private String dir;
    private ArrayList<Comment> comments;
    private MeetingLog Log = new MeetingLog();

    public Meeting() {
    }

    public Meeting(String startTime, String title, String dir, ArrayList<Comment> comments) {
        this.startTime = startTime;
        this.dir = dir;
        this.comments = new ArrayList<Comment>();
        this.title = title;
    }

    public void printMeeting() {
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
                    try {
                        SingletonJSON.getInstance().saveJson(project, user);
                    } catch (IOException | org.json.simple.parser.ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    read(user, project);
                    break;
                default:
                    break;
            }
        }
    }

    public Meeting(String startTime, String title) {
        this.startTime = startTime;
        this.title = title;
        this.comments = new ArrayList<Comment>();
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

    public void read(User user, Project project) {
        String sen;
        IRead proxy = new Proxy();
        sen = proxy.Load(dir);
        if (sen.equals("")) {
            return;
        } else {
            FacadeComment comment = new FacadeComment();
            comment.writeComment(this.comments, user, project);
        }
    }

    public void write(User user, Project project) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        System.out.println("Write Down");
        String text = sc.nextLine();
        
        System.out.println("fileName");
        Log.WriteMeetingLog(text, user, project);
        setDir(Log.getFileName());
    }

}
