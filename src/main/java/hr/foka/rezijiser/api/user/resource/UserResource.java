package hr.foka.rezijiser.api.user.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource {

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "surname", required = true)
    private String surname;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "time_created", required = true)
    private String timeCreated;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(UserResource.class.getName());
        builder.append(" [");
        builder.append("email=").append(email);
        builder.append(", name=").append(name);
        builder.append(", surname=").append(surname);
        builder.append(", timeCreated=").append(timeCreated);
        return builder.append("]").toString();
    }

}