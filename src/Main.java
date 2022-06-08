import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.Facade;
import classes.MementoProject;

import classes.auth.factoryMethod.ConcreteAuthActionFactory;
import classes.auth.strategy.SignWithAuthAction;

import classes.projectCRUD.ProjectEditor;
import classes.projectCRUD.factoryMethod.ConcreteProjectStrategyFactory;

import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonProject;
import classes.singleton.SingletonScanner;

// 회원가입, 로그아웃
// 프로젝트리스트
// 프로젝트 추가 삭제
// 멤버 초대
// task, subtask 추가

public class Main {
    private static ArrayList<MementoProject> savedProjects = new ArrayList<MementoProject>();

    public static void main(String[] args) {
        Scanner sc = SingletonScanner.getInstance().getScanner();
        SignWithAuthAction auth = new SignWithAuthAction();
        ConcreteAuthActionFactory af = new ConcreteAuthActionFactory();
        ProjectEditor pe = new ProjectEditor();
        ConcreteProjectStrategyFactory pf = new ConcreteProjectStrategyFactory();

        while (loginMenu(auth, sc, af)) {
            boolean flag = false;
            while (!flag) {
                printMenu();
                int tag = sc.nextInt();
                System.out.println("===========================");
                switch (tag) {
                    case 0:
                        try {
                            SingletonJSON.getInstance().saveJson(SingletonAuth.getInstance().getCurrentUser());
                            System.out.println("========== SAVE ===========");
                        } catch (IOException | ParseException e) {
                            System.out.println("========= FAILED TO SAVE ==========");
                            e.printStackTrace();
                        }
                        auth.setAuth(af.create("SignOut"));
                        flag = auth.authAction();
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
                        if (SingletonAuth.getInstance().getCurrentUser().getProjectIds().isEmpty()) {
                            System.out.println("======== THE PROJECT LIST IS EMPTY ========");
                        } else {
                            deleteProject(sc);
                        }
                        break;
                    case 4:
                        if (SingletonAuth.getInstance().getCurrentUser().getProjectIds().isEmpty()) {
                            System.out.println("======== THE PROJECT LIST IS EMPTY ========");
                        } else {
                            selectProject(sc);
                            if (SingletonProject.getInstance().getCurrentProject().getProjectId() != null) {
                                pe.setProjectStrategy(pf.create("u"));
                                pe.run();
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
                        auth.setAuth(af.create("UpdateUserInfo"));
                        while (auth.authAction()) {
                            auth.authAction();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static boolean loginMenu(SignWithAuthAction auth, Scanner sc, ConcreteAuthActionFactory af) {
        while (SingletonAuth.getInstance().getCurrentUser() == null) {
            printLoginMenu();
            int mode = sc.nextInt();
            switch (mode) {
                case 0:
                    sc.close();
                    System.out.println("========== EXIT ==========");
                    return false;

                case 1:
                    auth.setAuth(af.create("SignIn"));
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
                    auth.setAuth(af.create("SignUp"));
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
        System.out.print("Please Enter the index to delete: ");
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
        System.out.println("===========================");
        System.out.print("Please Enter the index : ");
        int indexToSelect = sc.nextInt();
        String selectedProjectId = SingletonAuth.getInstance().getCurrentUser().getProjectIds().get(indexToSelect - 1);
        SingletonProject.getInstance().setCurrentProject(SingletonJSON.getInstance().getProject(selectedProjectId));
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
        System.out.print("Please Enter the number : ");
    }

    private static void printLoginMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: SIGN IN");
        System.out.println("2: SIGN UP");
        System.out.println("===========================");
    }
}
