package hr.foka.rezijiser.persistence.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hr.foka.rezijiser.persistence.service.ZonedDateTimeConverter;

@Entity
public class Notification {

    public static enum Level{
        INFO, WARNING, CRITICAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "checked")
    private Boolean checked;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(nullable = true, insertable = false, updatable = false)
    private ZonedDateTime timeCreated;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(nullable = true, insertable = false, updatable = false)
    private ZonedDateTime timeModified;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Boolean isChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public ZonedDateTime getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public ZonedDateTime getTimeModified() {
        return this.timeModified;
    }

    public void setTimeModified(ZonedDateTime timeModified) {
        this.timeModified = timeModified;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(Notification.class.getSimpleName());
        builder.append("[");
        builder.append("id=").append(id);
        builder.append(", user=").append(user);
        builder.append(", level=").append(level);
        builder.append(", title=").append(title);
        //builder.append(", message=").append(message);
        builder.append(", timeCreated=").append(timeCreated);
        builder.append(", timeModified=").append(timeModified);
        builder.append("]");
        return builder.toString();
    }

}
