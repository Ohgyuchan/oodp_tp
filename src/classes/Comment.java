package classes;

import java.util.ArrayList;
import java.util.Scanner;

import classes.user.User;

public class Comment {
    private String writer;
<<<<<<< HEAD
    private String content;
=======
    private int num;
>>>>>>> 1ddfc66a4351aa2fe1ae4ce2388198f4c64b6a7f

    public Comment() {
    }

    public Comment(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

<<<<<<< HEAD
=======
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

>>>>>>> 1ddfc66a4351aa2fe1ae4ce2388198f4c64b6a7f
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

<<<<<<< HEAD
    public void printComment() {
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
=======
    @Override
    public String toString() {
        return "\n" + this.getWriter() + " : " + this.getContent();
    }

    private String content;
>>>>>>> 1ddfc66a4351aa2fe1ae4ce2388198f4c64b6a7f
}
