package hr.foka.rezijiser.api.login.resource;

public class LoginResponseResource {
    
    private String token;


    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(LoginResponseResource.class.getName());
        builder.append("[");
        builder.append("token=").append(token);
        builder.append("]");
        return builder.toString();
    }

}
