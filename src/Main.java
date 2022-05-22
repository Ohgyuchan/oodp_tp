import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.simple.parser.ParseException;

import classes.MainTask;
import classes.Project;
import classes.SingletonJSON;
import classes.State;
import classes.User;
import classes.auth.Login;

// 로그인 회원가입
// 프로젝트리스트
// 프로젝트 추가 삭제
// 멤버 초대
// task, subtask 추가

public class Main {
    private static User currentUser;
    private static Project currentProject;

    public static void main(String[] args) {
        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        while (!login.getIsLogin()) {
            try {
                login.login(sc);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }

        currentUser = login.getCurrentUser();
        currentProject = new Project();
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
                                    break;
                                case 1:
                                    currentProject.print();
                                    break;
                                case 2:
                                    createTask(sc);
                                    break;
                                case 3:
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
        sc.close();
    }

    private static void createProject(Scanner sc) {
        currentUser.print();
        Project newProject = new Project(sc, currentUser);
        currentUser.addProjectIds(newProject.getProjectId());
        try {
            SingletonJSON.getInstance().saveJson(newProject, currentUser);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void deleteProject(Scanner sc) {
        currentUser.printProjects();
        System.out.print("Pleas Enter the index to delete: ");
        int indexToDelete = sc.nextInt();
        currentUser.deleteProject(indexToDelete);

    }

    private static void selectProject(Scanner sc) {
        currentUser.printProjects();
        System.out.print("Pleas Enter the index: ");
        int indexToSelect = sc.nextInt();
        String selectedProjectId = currentUser.getProjectIds().get(indexToSelect);
        currentProject = SingletonJSON.getInstance().getProject(selectedProjectId);
    }

    private static void createTask(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine();
        for (int i = 0; i < State.values().length; i++) {
            System.out.println("Select State");
            System.out.println("(" + i + "): " + State.values()[i]);
        }
        currentProject.addTask(new MainTask(title, State.values()[sc.nextInt()]));
        try {
            SingletonJSON.getInstance().saveJson(currentProject, currentUser);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
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
        System.out.println("DEFAULT: PRINT MENU");
        System.out.println("===========================");
    }

    private static void printProjectMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: PRINT ALL");
        System.out.println("2: ADD TASK");
        System.out.println("3: INVITE A MEMBER");
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
