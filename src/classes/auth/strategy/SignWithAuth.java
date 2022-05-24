package classes.auth.strategy;

public class SignWithAuth {
    private Auth auth;

    public SignWithAuth(Auth auth) {
        this.auth = auth;
    }

    public Auth getAuth() {
        return this.auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    public void authAction() {
        auth.authAction();
    }

}
