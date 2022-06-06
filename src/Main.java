import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.Comment;
import classes.Facade;
import classes.MainTask;
import classes.MementoProject;
import classes.Project;
import classes.auth.AuthFactory;
import classes.auth.strategy.SignWithAuth;
import classes.singleton.SingletonAuth;
import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;
import classes.user.User;

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
                            if (currentProject.getProjectId() != null) {
                                //TODO: ProjectCommand
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

    private static boolean loginMenu(SignWithAuth auth, Scanner sc, AuthFactory af) {
        while (SingletonAuth.getInstance().getCurrentUser() == null) {
            printLoginMenu();
            int mode = sc.nextInt();
            switch (mode) {
                case 0:
                    sc.close();
                    System.out.println("========== EXIT ==========");
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
        currentProject = SingletonJSON.getInstance().getProject(selectedProjectId);
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
