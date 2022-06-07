package classes.auth.factoryMethod;

import classes.auth.strategy.AuthAction;
import classes.auth.strategy.SignInAction;
import classes.auth.strategy.SignOutAction;
import classes.auth.strategy.SignUpAction;
import classes.auth.strategy.UpdateUserInfoAction;

public class ConcreteAuthActionFactory extends AuthActionFactory{
	
	@Override
    public AuthAction createAuthAction(String name) {
        switch(name){
			case "SignIn": return SignInAction.getInstance();
			case "SignOut": return SignOutAction.getInstance();
			case "SignUp": return SignUpAction.getInstance();
			case "UpdateUserInfo": return UpdateUserInfoAction.getInstance();
		}
		return null;
	}
}
