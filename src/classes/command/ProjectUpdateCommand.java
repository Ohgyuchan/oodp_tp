package classes.command;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.MainTask;
import classes.Project;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;

public class ProjectUpdateCommand implements ProjectCommand {
    private Project project;

    ProjectUpdateCommand(Project project) {
        this.project = project;
    }

    @Override
    public void run() {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        printUpdateProjectMenu();
        int input = sc.nextInt();
        while (input != 0) {
            switch (input) {
                case 0:
                    break;
                case 1:
                    project.print();
                    break;
                case 2:
                    viewTasks(sc, );
                    break;
                case 3:
                    createTask(sc);
                    break;
                case 4:
                    inviteMember(sc);
                    break;
                default:
                    break;
            }
        }
        try {
            SingletonJSON.getInstance().saveJson(project);
        } catch (ConcurrentModificationException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void printUpdateProjectMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: CURRENT PROJECT INFO");
        System.out.println("2: CHANGE PROJECT'S NAME");
        System.out.println("3: VIEW ALL TASK");
        System.out.println("4: ADD TASK");
        System.out.println("5: INVITE A MEMBER");
        System.out.println("===========================");
        System.out.print("Please Enter the number: ");
    }

    private void viewTasks(Scanner sc) {
        boolean check = true;

        if (project.getTasks().isEmpty()) {
            System.out.println("");
            System.out.println("입력된 Task가 없습니다.");
            System.out.println("");
        } else {
            while (check) {
                System.out.println("===========================");
                for (MainTask mainTask : project.getTasks()) {
                    System.out.println(mainTask.toString());
                }
                printSelectTask();
                System.out.print("Please Enter the index : ");
                int taskInput = sc.nextInt();
                switch (taskInput) {
                    case 1:
                        viewTaskDetail(sc);
                        break;
                    case 0:
                        check = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
