package classes;

public abstract class AbstractTask {
    private String title;
    // private ArrayList<Comment> comments;
    // private Date dueDate;

    public AbstractTask() {
    }

    public AbstractTask(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // public ArrayList<Comment> getComments() {
    // return comments;
    // }
    //
    // public void setComments(ArrayList<Comment> comments) {
    // this.comments = comments;
    // }
    //
    // public Date getDueDate() {
    // return dueDate;
    // }
    //
    // public void setDueDate(Date dueDate) {
    // this.dueDate = dueDate;
    // }

    public void stateUpdate() {

    }

    public void writeComment() {

    }

    public void delete() {

    }

    public void edit() {

    }
}
