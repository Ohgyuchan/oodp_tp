package classes;

import java.util.ArrayList;
import java.util.Scanner;

import classes.user.User;

public class Comment {
    private String writer;
    private String content;

    public Comment() {
    }

    public Comment(String writer, String content) {
        this.writer = writer;
        this.content = content;
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
    
    public void printComment(){
    	System.out.println("===========================");
    	System.out.println("0: EXIT");
        System.out.println("1: WRITE COMMENT");
        System.out.println("2: VIEW COMMENT");
        System.out.println("===========================");
    }
    
    public void printComment(ArrayList<Comment> comments, Scanner sc, User user, Project project) {
    	
    	boolean FLAG = true;
        while (FLAG) {
            int TAG = sc.nextInt();
            printComment();
            switch (TAG) {
                case 0:
                    FLAG = false;
                    break;
                case 1:
                	this.writer = user.getId();
                	this.content = sc.nextLine();
                	comments.add(this);
                    break;
                case 2:
                	for(int i=0; i<comments.size(); i++) {
                		System.out.println("["+writer+"]");
                		System.out.println("- "+comments.get(i).content);
                	}
                    break;
                default:
                    break;
            }
        }
    	
    }
}
