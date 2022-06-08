package classes;

import java.util.ArrayList;
import java.util.Scanner;

import classes.user.User;

public class FacadeComment {
	Comment comment;

	public void writeComment(ArrayList<Comment> comments, Scanner sc, User user, Project project) {
		comment = new Comment();
		comment.printComment(comments, sc, user, project);
	}
}
