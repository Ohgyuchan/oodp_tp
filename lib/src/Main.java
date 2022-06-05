import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.Facade;
import classes.MainTask;
import classes.MementoProject;
import classes.OnGoing;
import classes.Project;
import classes.auth.AuthFactory;
import classes.auth.strategy.SignWithAuth;
import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;
import classes.user.User;

// 회원가입, 로그아웃
// 프로젝트리스트
// 프로젝트 추가 삭제
// 멤버 초대
// task, subtask 추가

public class Main {
    private static Project currentProject;
    private static ArrayList<MementoProject> savedProjects = new ArrayList<MementoProject>();

    public static void main(String[] args) {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        SignWithAuth auth = new SignWithAuth();
        AuthFactory af = new AuthFactory();

        while (loginMenu(auth, sc, af)) {
            boolean flag = true;
            while (flag) {
                printMenu();
                int tag = sc.nextInt();
                switch (tag) {
                    case 0:
                        try {
                            SingletonJSON.getInstance().saveJson(SingletonAuth.getInstance().getCurrentUser());
                            System.out.println("========= SAVE ==========");
                        } catch (IOException | ParseException e) {
                            System.out.println("========= FAILED TO SAVE ==========");
                            e.printStackTrace();
                        }
                        auth.setAuth(af.createAuth("SignOut"));
                        flag = !auth.authAction();
                        break;
                    case 1:
                        if (SingletonAuth.getInstance().getCurrentUser().getProjectIds().isEmpty()) {
                            System.out.println("======== THE PROJECT LIST IS EMPTY ========");
                        } else {
                            SingletonAuth.getInstance().getCurrentUser().printProjects();
                        }
                        break;
                    case 2:
                        createProject(sc);
                        break;
                    case 3:
                        deleteProject(sc);
                        break;
                    case 4:
                        if (SingletonAuth.getInstance().getCurrentUser().getProjectIds().isEmpty()) {
                            System.out.println("======== THE PROJECT LIST IS EMPTY ========");
                        } else {
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
                        }
                        break;
                    case 5:
                        if (SingletonAuth.getInstance().getCurrentUser().getProjectIds().isEmpty()) {
                            System.out.println("======== THE PROJECT LIST IS EMPTY ========");
                        } else {
                            restoreProjects(sc);
                        }
                        break;
                    case 6:
                        auth.setAuth(af.createAuth("UpdateUserInfo"));
                        auth.authAction();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static boolean loginMenu(SignWithAuth auth, Scanner sc, AuthFactory af) {
        while (SingletonAuth.getInstance().getCurrentUser() == null) {
            printLoginMenu();
            int mode = sc.nextInt();
            switch (mode) {
                case 0:
                    sc.close();
                    System.out.println("=====EXIT=====");
                    return false;

                case 1:
                    auth.setAuth(af.createAuth("SignIn"));
                    boolean result = auth.authAction();
                    if (result) {
                        if (SingletonAuth.getInstance().getCurrentUser() != null)
                            return result;
                        else {
                            System.out.println("===== Sign In Failed ======");
                            break;
                        }
                    }
                    System.out.println("===== Sign In Failed ======");
                    break;

                case 2:
                    auth.setAuth(af.createAuth("SignUp"));
                    if (auth.authAction()) {
                        System.out.println("===== Sign Up Success ======");
                    } else {
                        System.out.println("===== Sign Up Failed ======");
                    }
                    break;

                default:
                    break;
            }
        }
        return false;
    }

    private static void createProject(Scanner sc) {
        Facade facade = new Facade();
        facade.createProject(sc, SingletonAuth.getInstance().getCurrentUser());
    }

    private static void deleteProject(Scanner sc) {
        SingletonAuth.getInstance().getCurrentUser().printProjects();
        System.out.print("Pleas Enter the index to delete: ");
        int indexToDelete = sc.nextInt();
        storeProjects();
        SingletonAuth.getInstance().getCurrentUser().deleteProject(indexToDelete - 1);
    }

    private static void storeProjects() {
        savedProjects.add(SingletonAuth.getInstance().getCurrentUser().savetoMemento());
    }

    private static void restoreProjects(Scanner sc) {
        System.out.println("Input the index of stored lists");
        int i = sc.nextInt();
        SingletonAuth.getInstance().getCurrentUser().restoreFromMemento(savedProjects.get(i));
    }

    private static void selectProject(Scanner sc) {
        SingletonAuth.getInstance().getCurrentUser().printProjects();
        System.out.print("Pleas Enter the index: ");
        int indexToSelect = sc.nextInt();
        String selectedProjectId = SingletonAuth.getInstance().getCurrentUser().getProjectIds().get(indexToSelect - 1);
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
                    System.out.print("Enter Subtask Title : ");
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
            SingletonJSON.getInstance().saveJson(currentProject, SingletonAuth.getInstance().getCurrentUser());
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

    }

    private static void viewTasks(Scanner sc, int index) {
        boolean check = true;

        if (currentProject.getTasks().isEmpty()) {
            System.out.println("");
            System.out.println("입력된 Task가 없습니다.");
            System.out.println("");
        } else {
            while (check) {
                System.out.println("===========================");
                for (MainTask p : currentProject.getTasks()) {
                    System.out.println(p.toString());
                    System.out.println();
                }
                System.out.println("===========================");
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
        System.out.println("ENTER THE INDEX");
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
            User user = SingletonAuth.getInstance().getCurrentUser();

            switch (inputIndex) {
                case 1:
                    checkingTask(sc, taskIndex);
                    break;
                case 2:
                    checkingSubTask(sc, taskIndex);
                    break;
                case 3:
                    currentProject.getTasks().get(taskIndex).addMeeting(sc);
                    break;
                case 4:
                    currentProject.getTasks().get(taskIndex).getMeetings().get(0).printMeeting(sc, user,
                            currentProject);
                    break;
                case 5:
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
            currentProject = SingletonJSON.getInstance().invite(sc, currentProject);
        } catch (ConcurrentModificationException | IOException | ParseException e) {
        }

    }

    private static void printMenu() {
        System.out.println("===========================");
        System.out.println("0: SIGN OUT");
        System.out.println("1: PRINT MY PROJECTS");
        System.out.println("2: CREATE A PROJECT");
        System.out.println("3: DELETE A PROJECT");
        System.out.println("4: SELECT A PROJECT");
        System.out.println("5: RESTORE A PROJECTS");
        System.out.println("6: UPDATE USER INFO");
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
        System.out.println("3: ADD MEETING");
        System.out.println("4: EDIT MEETNG");
        System.out.println("5: EXIT");
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
