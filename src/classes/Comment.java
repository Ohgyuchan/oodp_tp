package classes;

import java.util.ArrayList;
import java.util.Scanner;

import classes.singleton.SingletonScanner;
import classes.user.User;

public class Comment {
    private String writer;
    private String content;
    private int num;

    public Comment() {
    }

    public Comment(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void printComment() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: WRITE COMMENT");
        System.out.println("2: VIEW COMMENT");
        System.out.println("===========================");
    }

    public void printComment(ArrayList<Comment> comments, User user, Project project) {

        boolean FLAG = true;
        Scanner sc = SingletonScanner.getInstance().getScanner();
        while (FLAG) {
            printComment();
            int TAG = sc.nextInt();
            switch (TAG) {
                case 0:
                    FLAG = false;
                    break;
                case 1:
                    this.writer = user.getId();
                    System.out.println("write comment: ");
                    this.content = sc.nextLine();
                    this.content = sc.nextLine();
                    comments.add(this);
                    break;
                case 2:
                    for (int i = 0; i < comments.size(); i++) {
                        System.out.println("[" + writer + "]");
                        System.out.println("- " + comments.get(i).content);
                    }
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public String toString() {
        return "\n" + this.getWriter() + " : " + this.getContent();
    }
    
    public void turnOn() {
        System.out.print("\n" + this.getWriter() + " : " + this.getContent());
    }
}
