package classes;

import classes.user.Member;

public class Comment {
    private String writer;
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

    @Override
    public String toString() {
        return "\n" + this.getWriter() + " : " + this.getContent();
    }

    private String content;
}
