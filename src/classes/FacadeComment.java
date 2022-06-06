package classes;

import java.util.ArrayList;

import classes.user.User;

public class FacadeComment {
	Comment comment;

	public void writeComment(ArrayList<Comment> comments, User user, Project project) {
		comment = new Comment();
		comment.printComment(comments, user, project);
	}
}
