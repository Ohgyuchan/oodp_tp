package classes.auth.strategy;

public class SignWithAuth {
    private Auth auth;

    public SignWithAuth() {
        
    }

    public SignWithAuth(Auth auth) {
        this.auth = auth;
    }

    public Auth getAuth() {
        return this.auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    public boolean authAction() {
        return auth.authAction();
    }

}
