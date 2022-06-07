package classes.auth.strategy;

public class SignWithAuthAction {
    private AuthAction auth;

    public SignWithAuthAction() {

    }

    public SignWithAuthAction(AuthAction auth) {
        this.auth = auth;
    }

    public AuthAction getAuth() {
        return this.auth;
    }

    public void setAuth(AuthAction auth) {
        this.auth = auth;
    }

    public boolean authAction() {
        return auth.authAction();
    }

}
