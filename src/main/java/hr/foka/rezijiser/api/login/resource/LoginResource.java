package hr.foka.rezijiser.api.login.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResource {
    
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(LoginResource.class.getSimpleName());
        builder.append(" [");
        builder.append("email=").append(email);
        //builder.append(", password=").append(password);
        builder.append("]");
        return builder.toString();
    }
}
