package hr.foka.rezijiser.security.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResource {
    
    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "password")
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("LoginResource [");
        builder.append(super.toString());
        builder.append("username=").append(username);
        //builder.append(", password=").append(password);
        builder.append("]");
        return builder.toString();
    }
}
