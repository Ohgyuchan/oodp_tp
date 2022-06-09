package classes.projectCRUD.strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.Command;
import classes.Comment;
import classes.CommentToStringCommand;
import classes.MainTask;
import classes.MeetingMemento;
import classes.Project;
import classes.TaskToStringCommand;
import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonProject;
import classes.singleton.SingletonScanner;
import classes.user.Button;

public class ProjectUpdateStrategy implements ProjectEditStrategy {
    private static ProjectEditStrategy instance = null;
    private Project project;
    private ArrayList<MeetingMemento> savedMeetings = new ArrayList<>();

    private ProjectUpdateStrategy() {
    }

    public static ProjectEditStrategy getInstance() {
        if (instance == null) {
            instance = new ProjectUpdateStrategy();
        }
        return instance;
    }

    @Override
    public void run() {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        project = SingletonProject.getInstance().getCurrentProject();
        boolean FLAG = false;
        while (!FLAG) {
            printUpdateProjectMenu();
            int input = sc.nextInt();
            switch (input) {
                case 0:
                    FLAG = !FLAG;
                    break;
                case 1:
                    project.print();
                    break;
                case 2:
                    System.out.print("새이름: ");
                    String newName = sc.next();
                    project.setProjectName(newName);
                    break;
                case 3:
                    viewTasks(sc);
                    break;
                case 4:
                    createTask(sc);
                    break;
                case 5:
                    inviteMember(sc);
                    break;
                default:
                    break;
            }
            SingletonProject.getInstance().setCurrentProject(project);
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
                    Command taskToStringCommand = new TaskToStringCommand(mainTask);
                    Button button = new Button(taskToStringCommand);
                    button.pressed();
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

    private void viewTaskDetail(Scanner sc) {
        boolean check = true;
        System.out.println("===========================");
        for (MainTask p : project.getTasks()) {
            System.out.println(p.toString());
        }
        System.out.println("===========================");
        System.out.print("Please Enter the index of the task : ");
        int taskIndex = sc.nextInt();
        System.out.println("===========================");
        System.out.println("Task : " + project.getTasks().get(taskIndex).getTitle() + " > State : "
                + project.getTasks().get(taskIndex).getState());
        System.out.println("SubTask" + project.getTasks().get(taskIndex).getSubTasks());

        while (check) {
            printStateChange();
            System.out.println("===========================");
            System.out.print("Please Enter the index : ");
            int inputIndex = sc.nextInt();

            switch (inputIndex) {
                case 1:
                    System.out.println("===========================");
                    System.out.println("Task : " + project.getTasks().get(taskIndex).getTitle() + " > State : "
                            + project.getTasks().get(taskIndex).getState());
                    checkingTask(sc, taskIndex);
                    break;
                case 2:
                    System.out.println("===========================");
                    System.out.println("Task : " + project.getTasks().get(taskIndex).getTitle() + " > State : "
                            + project.getTasks().get(taskIndex).getState());
                    System.out.println("SubTask");
                    System.out.println(project.getTasks().get(taskIndex).getSubTasks());
                    checkingSubTask(sc, taskIndex);
                    break;
                case 3:
                    project.getTasks().get(taskIndex).addMeeting(sc);
                    break;
                case 4:
                    project.getTasks().get(taskIndex).meetingList();
                    if (project.getTasks().get(taskIndex).getMeetings().isEmpty()) {
                        System.out.println("there is no meeting schedule");
                        break;
                    }
                    System.out.println("please enter the index:");
                    int index = sc.nextInt();
                    project.getTasks().get(taskIndex).getMeetings().get(index).printMeeting(sc,
                            SingletonAuth.getInstance().getCurrentUser(),
                            project);
                    break;
                case 5:
                    viewSubTaskDETAIL(sc, taskIndex);
                    break;
                case 6:
                    project.getTasks().get(taskIndex).deleteMeeting(sc);
                    break;
                case 7:
                    savedMeetings.add(project.getTasks().get(taskIndex).createMemento());
                    break;
                case 8:
                    if (savedMeetings.isEmpty()) {
                        System.out.println("======== THE STORED LIST IS EMPTY ========");
                    } else {
                        for (int i = 0; i < savedMeetings.size(); i++) {
                            System.out.print(i + 1 + ": ");
                            for (int j = 0; j < savedMeetings.get(i).getSavedMeet().size(); j++) {
                                System.out.print(savedMeetings.get(i).getSavedMeet().get(j).getTitle());
                                if (j != savedMeetings.get(i).getSavedMeet().size() - 1) {
                                    System.out.print(" ,");
                                }
                            }
                            System.out.println();
                        }
                        System.out.print("복구할 리스트 입력: ");
                        int sindex = sc.nextInt();
                        project.getTasks().get(taskIndex).restoreMeeting(savedMeetings.get(sindex - 1));
                    }
                    break;
                case 0:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void viewSubTaskDETAIL(Scanner sc, int taskIndex) {
        System.out.println("===========================");
        System.out.println("Task : " + project.getTasks().get(taskIndex).getTitle() + " > State : "
                + project.getTasks().get(taskIndex).getState());
        System.out.println("SubTask");
        System.out.println(project.getTasks().get(taskIndex).getSubTasks());
        System.out.println("===========================");
        System.out.print("Please Enter the index of the Subtask : ");
        int inputIndex = sc.nextInt();
        System.out.println("===========================");

        System.out.println(
                "SubTask : " + project.getTasks().get(taskIndex).getSubTasks().get(inputIndex).toString());
        System.out.println("Comment : ");
        for (Comment cmt : project.getTasks().get(taskIndex).getSubTasks().get(inputIndex).getComments()) {
            Command commentToStringCommand = new CommentToStringCommand(cmt);
            Button buttonTwo = new Button(commentToStringCommand);
            buttonTwo.pressed();
        }
        System.out.println("");
        commentWrite(sc, inputIndex, taskIndex);
    }

    private void commentWrite(Scanner sc, int inputIndex, int taskIndex) {

        commentWriteMenu();
        System.out.println("===========================");
        System.out.print("Please Enter the index : ");
        int inputIndex2 = sc.nextInt();
        switch (inputIndex2) {
            case 1:
                System.out.println("===========================");
                System.out.print("COMMENT : ");
                sc.nextLine();
                String commentInput = sc.nextLine();
                project.getTasks().get(taskIndex).getSubTasks().get(inputIndex).setComments(commentInput,
                        SingletonAuth.getInstance().getCurrentUser().getDisplayName(),
                        project.getTasks().get(taskIndex).getSubTasks().get(inputIndex).getComments().size());
                try {
                    SingletonJSON.getInstance().saveJson(project, SingletonAuth.getInstance().getCurrentUser());
                } catch (IOException | org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("===========================");
                System.out.println("SubTask : "
                        + project.getTasks().get(taskIndex).getSubTasks().get(inputIndex).toString());
                System.out.print("Comment : ");
                for (Comment cmt : project.getTasks().get(taskIndex).getSubTasks().get(inputIndex)
                        .getComments()) {
                    System.out.print(cmt.toString());
                }
                System.out.println("");
                break;
            case 0:
                break;
            default:
                break;
        }

    }

    private void commentWriteMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: WRITING COMMENTS");
    }

    private void checkingTask(Scanner sc, int index) {
        printState();
        sc.nextLine();
        System.out.print("Please Enter the index : ");
        int inputIndex = sc.nextInt();

        switch (inputIndex) {
            case 1:
                System.out.println("===========================");
                project.getTasks().get(index).setState("진행중"); // State Pattern

                try {
                    SingletonJSON.getInstance().saveJson(project, SingletonAuth.getInstance().getCurrentUser());
                } catch (IOException | org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Task : " + project.getTasks().get(index).getTitle() + " > State : "
                        + project.getTasks().get(index).getState());
                System.out.println("SubTask");
                System.out.println(project.getTasks().get(index).getSubTasks());
                break;
            case 2:
                System.out.println("===========================");
                project.getTasks().get(index).upgradeComplete();
                System.out.println(project.getTasks().get(index));
                try {
                    SingletonJSON.getInstance().saveJson(project, SingletonAuth.getInstance().getCurrentUser());
                } catch (IOException | org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 0:
                break;
        }
    }

    private void checkingSubTask(Scanner sc, int index) {
        System.out.println("===========================");
        System.out.print("Please Enter the index of the Subtask : ");
        sc.nextLine();
        int subtaskIndex = sc.nextInt();

        System.out.println("===========================");
        System.out.println("Task : " + project.getTasks().get(index).getTitle() + " > State : "
                + project.getTasks().get(index).getState());
        System.out.println("SubTask");
        System.out.println(project.getTasks().get(index).getSubTasks());
        printState();
        sc.nextLine();
        System.out.print("Please Enter the index : ");
        int inputIndex = sc.nextInt();

        switch (inputIndex) {
            case 1:
                project.getTasks().get(index).getSubTasks().get(subtaskIndex).setState("진행중");
                try {
                    SingletonJSON.getInstance().saveJson(project, SingletonAuth.getInstance().getCurrentUser());
                } catch (IOException | org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("===========================");
                System.out.println("Task : " + project.getTasks().get(index).getTitle() + " > State : "
                        + project.getTasks().get(index).getState());
                System.out.println("SubTask");
                System.out.println(project.getTasks().get(index).getSubTasks());
                break;
            case 2:
                project.getTasks().get(index).getSubTasks().get(subtaskIndex).setState("완료");
                try {
                    SingletonJSON.getInstance().saveJson(project, SingletonAuth.getInstance().getCurrentUser());
                } catch (IOException | org.json.simple.parser.ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("===========================");
                System.out.println("Task : " + project.getTasks().get(index).getTitle() + " > State : "
                        + project.getTasks().get(index).getState());
                System.out.println("SubTask");
                System.out.println(project.getTasks().get(index).getSubTasks());
                break;
            case 0:
                break;
        }
    }

    private void createTask(Scanner sc) {
        MainTask t = new MainTask("");
        boolean check = true;

        System.out.println("===========================");
        System.out.print("Enter the Task Title: ");
        sc.nextLine();
        String title = sc.nextLine();
        t.setTitle(title);
        t.setNum(project.getTasks().size());

        while (check) {
            printInputSubtask();
            System.out.print("Please Enter the index : ");
            int subtaskInput = sc.nextInt();

            switch (subtaskInput) {
                case 1:
                    System.out.println("===========================");
                    System.out.print("Enter Subtask Title : ");
                    sc.nextLine();
                    String subTitle = sc.nextLine();
                    System.out.print("Enter The Person in Charge : ");
                    String person = sc.nextLine();
                    t.setSubTask(subTitle, person, t.getSubTasks().size());
                    break;
                case 0:
                    System.out.println("===========================");
                    System.out.println("[SAVE] Subtask");
                    check = false;
                    break;
                default:
                    System.out.println("===========================");
                    System.out.println("[ERROR] Retry");
                    break;
            }
        }
        project.addTask(t);
        project.getTasks().sort(Comparator.comparing(MainTask::getNum));

        try {
            SingletonJSON.getInstance().saveJson(project, SingletonAuth.getInstance().getCurrentUser());
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    private void printInputSubtask() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: ADD SUBTASK");
        System.out.println("===========================");
    }

    public void inviteMember(Scanner sc) {
        try {
            project = SingletonJSON.getInstance().invite(sc, project);
        } catch (ConcurrentModificationException | IOException | ParseException e) {
        }

    }

    private void printSelectTask() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: VIEW TASK DETAIL");
        System.out.println("===========================");
    }

    private void printStateChange() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: CHANGE TASK'S STATE");
        System.out.println("2: CHANGE SUBTASK'S STATE");
        System.out.println("3: ADD MEETING");
        System.out.println("4: EDIT MEETING");
        System.out.println("5: VIEW SUBTASK'S DETAIL");
        System.out.println("6: DELETE MEETING");
        System.out.println("7: STORE MEETING");
        System.out.println("8: RESTORE MEETING");
        System.out.println("===========================");
    }

    private void printState() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: ON-GOING");
        System.out.println("2: COMPLETE");
        System.out.println("===========================");
    }
}
