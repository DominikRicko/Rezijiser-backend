package hr.foka.rezijiser.api.notification.resource;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import hr.foka.rezijiser.persistence.domain.Notification.Level;

public class NotificationResource {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private Level level;

    @JsonProperty("title")
    private String title;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timeCreated")
    private ZonedDateTime timeCreated;

    @JsonProperty("checked")
    private Boolean checked;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Boolean isChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(NotificationResource.class.getName());
        builder.append(" [");
        builder.append("id=").append(id);
        builder.append(", level=").append(level);
        builder.append(", title=").append(title);
        //builder.append(", message=").append(message);
        builder.append(", timeCreated=").append(timeCreated);
        builder.append(", checked=").append(checked);
        builder.append("]");
        return builder.toString();
    }

}
