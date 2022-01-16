package hr.foka.rezijiser.api.registration.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationResource {

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "surname", required = true)
    private String surname;

    @JsonProperty(value = "password", required = true)
    private String rawPassword;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRawPassword() {
        return this.rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(RegistrationResource.class.getSimpleName());
        builder.append(" [");
        builder.append("name=").append(name);
        builder.append(", surname=").append(surname);
        builder.append(", email=").append(email);
        builder.append("]");
        return builder.toString();
    }

}