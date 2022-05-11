package classes;

public class Comment {
    private Member writer;

    public Comment() {
    }

    public Comment(Member writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
