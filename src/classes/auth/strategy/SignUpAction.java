package classes.auth.strategy;

public class SignUpAction implements Auth{
    @Override
    public boolean authAction() {
        System.out.println("SignUp");
        return true;
    }
}
