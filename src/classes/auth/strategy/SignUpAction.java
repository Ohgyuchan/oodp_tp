package classes.auth.strategy;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import classes.singleton.SingletonJSON;
import classes.singleton.SingletonScanner;
import classes.user.User;

public class SignUpAction implements Auth {
    @Override
    public boolean authAction() {
        System.out.println("====== SignUp ======");
        User newUser = new User(SingletonScanner.getInstance().getScanner());
        ArrayList<User> users = new ArrayList<>();
        users = SingletonJSON.getInstance().getUserList();
        for (User user : users) {
            if (user.getId().equals(newUser.getId())) {
                System.out.println("====== 중복된 ID =======");

                return false;
            }
        }
        try {
            SingletonJSON.getInstance().saveJson(newUser);
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
