import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.Project;
import classes.User;
import classes.auth.Login;

// 로그인 회원가입
// 프로젝트리스트
// 프로젝트 추가 삭제
// 멤버 초대
// task, subtask 추가

public class Main {
    private static User currentUser;

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
        boolean flag = true;
        printMenu();
        while (flag) {
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
                    break;
                default:
                    printMenu();
                    break;
            }
        }
        sc.close();
    }

    private static void createProject(Scanner sc) {
        Project newProject = new Project(sc, currentUser);
        currentUser.addProjects(newProject);
    }

    private static void deleteProject(Scanner sc) {
        currentUser.printProjects();
        System.out.print("Pleas Enter the index to delete: ");
        int indexToDelete = sc.nextInt();
        currentUser.deleteProject(indexToDelete);
        
    }

    private static void selectProject(Scanner sc) {
        int index = sc.nextInt();
        currentUser.getProjects().get(index).menu(sc);
    }

    private static void printMenu() {
        System.out.println("===========================");
        System.out.println("0: EXIT");
        System.out.println("1: PRINT MY PROJECTS");
        System.out.println("2: CREATE A PROJECT");
        System.out.println("3: DELETE A PROJECT");
        System.out.println("4: SELECT A PROJECT");
        System.out.println("5: PRINT MENU");
        System.out.println("===========================");
    }
}
