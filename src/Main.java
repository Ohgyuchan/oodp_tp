import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import classes.Facade;
import classes.MainTask;
import classes.MementoProject;
import classes.OnGoing;
import classes.Project;
import classes.user.User;
import classes.auth.strategy.SignInAction;
import classes.auth.strategy.SignOutAction;
import classes.auth.strategy.SignUpAction;
import classes.auth.strategy.SignWithAuth;
import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;

// 로그인 회원가입
// 프로젝트리스트
// 프로젝트 추가 삭제
// 멤버 초대
// task, subtask 추가

public class Main {
    private static Project currentProject;
    private static User currentUser;
    private ArrayList<MementoProject> savedProjects = new ArrayList<MementoProject>();

    public static void main(String[] args) {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        SignWithAuth sign = new SignWithAuth();

        printLoginMenu();
        int mode = sc.nextInt();
        while (currentUser == null) {
            switch (mode) {
                case 0:
                    mode = 0;
                    sc.close();
                    System.out.println("=====EXIT=====");
                    return;

                case 1:
                    sign.setAuth(new SignInAction());
                    sign.authAction();
                    currentUser = SingletonAuth.getInstance().getCurrentUser();
                    break;

                case 2:
                    sign.setAuth(new SignUpAction());
                    sign.authAction();
                    break;

                default:
                    printMenu();
                    break;
            }
        }

        boolean flag = true;
        while (flag) {
            printMenu();
            int tag = sc.nextInt();
            switch (tag) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    currentUser.printProjects();
                    break;
                case 2:
                    createProject(sc);
                    break;
                case 3:
                    deleteProject(sc);
                    break;
                case 4:
                    selectProject(sc);
                    if (currentProject.getProjectId() != null) {
                        boolean FLAG = true;
                        while (FLAG) {
                            printProjectMenu();
                            int TAG = sc.nextInt();
                            switch (TAG) {
                                case 0:
                                    FLAG = false;
                                    currentProject.init();
                                    break;
                                case 1:
                                    currentProject.print();
                                    break;
                                case 2:
                                    viewTasks(sc, tag);
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
                    }
                    break;
                default:
                    break;
            }
        }
        sign.setAuth(new SignOutAction());
        sign.authAction();
        System.out.println("=====EXIT=====");
        sc.close();
    }

    private static void createProject(Scanner sc) {
        Facade facade = new Facade();
        facade.createProject(sc, currentUser);
    }

    private static void deleteProject(Scanner sc) {
        currentUser.printProjects();
        System.out.print("Pleas Enter the index to delete: ");
        int indexToDelete = sc.nextInt();
        currentUser.deleteProject(indexToDelete);
    }

    private void storeProjects() {
        savedProjects.add(currentUser.savetoMemento());
    }

    private void restoreProjects(Scanner sc) {
        System.out.println("input the index of stored lists");
        int i = sc.nextInt();
        currentUser.restoreFromMemento(savedProjects.get(i));
    }

    private static void selectProject(Scanner sc) {
        currentUser.printProjects();
        System.out.print("Pleas Enter the index: ");
        int indexToSelect = sc.nextInt();
        String selectedProjectId = currentUser.getProjectIds().get(indexToSelect - 1);
        currentProject = SingletonJSON.getInstance().getProject(selectedProjectId);
    }

    private static void createTask(Scanner sc) {
        MainTask t = new MainTask("");
        boolean check = true;

        System.out.print("Enter the Task Title: ");
        sc.nextLine();
        String title = sc.nextLine();
        t.setTitle(title);
        t.setNum(currentProject.getTasks().size());

        while (check) {
            printInputSubtask();
            int subtaskInput = sc.nextInt();

            switch (subtaskInput) {
                case 1:
                    System.out.print("Subtask Title 입력 : ");
                    sc.nextLine();
                    String subTitle = sc.nextLine();
                    t.setSubTasks(subTitle, t.getSubTasks().size());
                    break;
                case 2:
                    System.out.println("[SAVE] Subtask");
                    check = false;
                    break;
                default:
                    System.out.println("[ERROR] Retry");
                    break;
            }
        }
        currentProject.addTask(t);
        currentProject.getTasks().sort(Comparator.comparing(MainTask::getNum));

        try {
            SingletonJSON.getInstance().saveJson(currentProject, currentUser);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    private static void viewTasks(Scanner sc, int index) {
        boolean check = true;

        if (currentProject.getTasks().size() == 0) {
            System.out.println("");
            System.out.println("입력된 Task가 없습니다.");
            System.out.println("");
        } else {
            System.out.println("===========================");
            for (MainTask p : currentProject.getTasks()) {
                System.out.println(p.toString());
            }
            System.out.println("===========================");
            while (check) {
                printSelectTask();
                int taskInput = sc.nextInt();

                switch (taskInput) {
                    case 1:
                        viewTaskDetail(sc);
                        break;
                    case 2:
                        check = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void viewTaskDetail(Scanner sc) {
        boolean check = true;
        System.out.println("1: ENTER THE INDEX");
        int taskIndex = sc.nextInt();

        System.out.println("");
        System.out.println("Task : " + currentProject.getTasks().get(taskIndex).getTitle());
        System.out.println("SubTask");
        System.out.println(currentProject.getTasks().get(taskIndex).getSubTasks());
        System.out.println("");

        while (check) {
            printStateChange();
            sc.nextLine();
            int inputIndex = sc.nextInt();

            switch (inputIndex) {
                case 1:
                    checkingTask(sc, taskIndex);
                    break;
                case 2:
                    checkingSubTask(sc, taskIndex);
                    break;
                case 3:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }

    private static void checkingTask(Scanner sc, int index) {
        System.out.println("");
        OnGoing onGoing = new OnGoing();
        printState();
        sc.nextLine();
        int inputIndex = sc.nextInt();

        switch (inputIndex) {
            case 1:
                currentProject.getTasks().get(index).setTaskState(onGoing); // State Pattern
                break;
            case 2:
                currentProject.getTasks().get(index).upgradeComplete();
                ; // Observer Pattern
                break;
            case 3:
                break;
        }
    }

    private static void checkingSubTask(Scanner sc, int index) {
        System.out.println("");
        System.out.print("Subtask 선택 : ");
        sc.nextLine();
        int subtaskIndex = sc.nextInt();

        printState();
        sc.nextLine();
        int inputIndex = sc.nextInt();

        switch (inputIndex) {
            case 1:
                currentProject.getTasks().get(index).getSubTasks().get(subtaskIndex).setState("진행중");
                break;
            case 2:
                currentProject.getTasks().get(index).getSubTasks().get(subtaskIndex).setState("완료");
                break;
            case 3:
                break;
        }
    }

    public static void inviteMember(Scanner sc) {
        try {
            SingletonJSON.getInstance().invite(sc, currentProject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: PRINT MY PROJECTS");
        System.out.println("2: CREATE A PROJECT");
        System.out.println("3: DELETE A PROJECT");
        System.out.println("4: SELECT A PROJECT");
        // System.out.println("5: STORE A PROJECTS");
        // System.out.println("6: Restore A PROJECTS");
        System.out.println("DEFAULT: PRINT MENU");
        System.out.println("===========================");
    }

    private static void printProjectMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: CURRENT PROJECT INFO");
        System.out.println("2: VIEW ALL TASK");
        System.out.println("3: ADD TASK");
        System.out.println("4: INVITE A MEMBER");
        System.out.println("===========================");
    }

    private static void printInputSubtask() {
        System.out.println("===========================");
        System.out.println("1: ADD SUBTASK");
        System.out.println("2: EXIT");
        System.out.println("===========================");
    }

    private static void printSelectTask() {
        System.out.println("===========================");
        System.out.println("1: VIEW TASK DETAIL");
        System.out.println("2: EXIT");
        System.out.println("===========================");
    }

    private static void printStateChange() {
        System.out.println("===========================");
        System.out.println("1: CHANGE TASK'S STATE");
        System.out.println("2: CHANGE SUBTASK'S STATE");
        System.out.println("3: EXIT");
        System.out.println("===========================");
    }

    private static void printState() {
        System.out.println("===========================");
        System.out.println("1: ON-GOING");
        System.out.println("2: COMPLETE");
        System.out.println("3: EXIT");
        System.out.println("===========================");
    }

    private static void printLoginMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: SIGN IN");
        System.out.println("2: SIGN UP");
        System.out.println("===========================");
    }
}
