import classes.auth.Login;

// 로그인 회원가입
// 프로젝트리스트
// 프로젝트 추가 삭제
// 멤버 초대
// task, subtask 추가

public class Main {

    public static void main(String[] args) {
        Login login = new Login();

        while(!login.getIsLogin()) {
            login = new Login();
        }

        while(login.getIsLogin()) {

        }
    }
}
