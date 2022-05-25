package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Facade {
	private String title = "예시 task";
	private State state = State.TODO;
			
	
	public void createProject(Scanner sc,User user) {
		Project project = new Project(sc, user);
		MainTask task = new MainTask(title, state);
		project.addTask(task);
		System.out.println("Project 생성");
	}
	
	public void createProjectWithName(String name,ArrayList<String> memberIds, String leaderId) {
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
