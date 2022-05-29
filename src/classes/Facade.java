package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.singleton.SingletonJSON;

public class Facade {
	private String title = "예시 task";
	private State state = State.TODO;
			
	
	public void createProject(Scanner sc, User user) {
		user.print();
		Project project = new Project(sc, user);
		MainTask task = new MainTask(title, state);
		project.addTask(task);
		user.addProjectIds(project.getProjectId());
		try {
            SingletonJSON.getInstance().saveJson(project, user);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
		System.out.println("Project 생성");
	}
	
	public void createProjectWithName(String name, ArrayList<String> memberIds, String leaderId) {
		Project project = new Project(name, memberIds, leaderId);
		MainTask task = new MainTask(title, state);
		project.addTask(task);
		System.out.println("Project 생성");
	}
	
	public void createTask() {
		System.out.println("Task 생성");
	}
	
	public void createSubTask() {
		System.out.println("SubTask 생성");
	}
}
