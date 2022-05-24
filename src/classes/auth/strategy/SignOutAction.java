package classes.auth.strategy;

public class SignOutAction implements Auth{
    @Override
    public boolean authAction() {
        System.out.println("SignOut");
        return true;
    }
}
