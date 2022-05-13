import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import classes.User;
import classes.auth.Login;

// 로그인 회원가입
// 프로젝트리스트
// 프로젝트 추가 삭제
// 멤버 초대
// task, subtask 추가

public class Main {

    public static void main(String[] args) {
        Login login = new Login();

        while (!login.getIsLogin()) {
            try {
                login.login();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }

        User currentUser = login.getCurrentUser();
        boolean flag = true;
        printMenu();
        Scanner sc = new Scanner(System.in);
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
                    createProject();
                    break;
                case 3:
                    deleteProject();
                    break;
                case 4:
                    selectProject();
                    break;
                default:
                    printMenu();
                    break;
            }
        }
        sc.close();
    }

    private static void createProject() {

    }

    private static void deleteProject() {

    }

    private static void selectProject() {

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
