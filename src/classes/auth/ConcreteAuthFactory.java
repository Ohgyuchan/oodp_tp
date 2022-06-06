package classes.auth;

import classes.auth.strategy.Auth;
import classes.auth.strategy.SignInAction;
import classes.auth.strategy.SignOutAction;
import classes.auth.strategy.SignUpAction;
import classes.auth.strategy.UpdateUserInfoAction;

public class ConcreteAuthFactory extends AuthFactory{
	
	@Override
    public Auth createAuth(String name) {
        switch(name){
			case "SignIn": return SignInAction.getInstance();
			case "SignOut": return SignOutAction.getInstance();
			case "SignUp": return SignUpAction.getInstance();
			case "UpdateUserInfo": return UpdateUserInfoAction.getInstance();
		}
		return null;
	}
}
