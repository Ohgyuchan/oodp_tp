package classes;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.singleton.SingletonJSON;
import classes.user.User;

public class Facade {
	private String title = "예시 task";

	public void createProject(Scanner sc, User user) {
		System.out.println("======= CREATE NEW PROJECT =======");
		Project project = new Project(sc, user);
		MainTask task = new MainTask(title);
		project.addTask(task);
		user.addProjectIds(project.getProjectId());
		try {
			SingletonJSON.getInstance().saveJson(project, user);
		} catch (IOException | ParseException | ConcurrentModificationException | NullPointerException e) {
			e.getMessage();
		}
		System.out.println("====== Project 생성 ======");
	}
}
