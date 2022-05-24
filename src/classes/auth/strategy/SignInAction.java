package classes.auth.strategy;

public class SignInAction implements Auth{
    @Override
    public boolean authAction() {
        System.out.println("SignIn");
        return true;
    }
}
