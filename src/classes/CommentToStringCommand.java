package classes;

public class CommentToStringCommand implements Command {
    private Comment theComment;
    public CommentToStringCommand(Comment comment) { this.theComment = comment; }
    public void execute() { theComment.turnOn(); }
}
