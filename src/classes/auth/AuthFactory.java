package classes.auth;

import classes.auth.strategy.Auth;
import classes.auth.strategy.SignInAction;
import classes.auth.strategy.SignOutAction;
import classes.auth.strategy.SignUpAction;
import classes.auth.strategy.UpdateUserInfoAction;

public class AuthFactory {
    public Auth createAuth(String name) {
        switch(name){
			case "SignIn": return new SignInAction();
			case "SignOut": return new SignOutAction();
			case "SignUp": return new SignUpAction();
			case "UpdateUserInfo": return new UpdateUserInfoAction();
		}
		return null;
	}
}
