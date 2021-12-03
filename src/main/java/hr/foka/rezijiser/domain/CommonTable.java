package hr.foka.rezijiser.domain;

import java.time.ZonedDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class CommonTable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ZonedDateTime timeCreated;

    private ZonedDateTime timeModified;

    public Long getId() {
        return id;
    }

    public ZonedDateTime getTimeCreated() {
        return timeCreated;
    }
    
    public ZonedDateTime getTimeModified() {
        return timeModified;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeModified(ZonedDateTime timeModified) {
        this.timeModified = timeModified;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("CommonTable [");
        builder.append("id=").append(id);
        builder.append(", timeCreated").append(timeCreated);
        builder.append(", timeModified").append(timeModified);
        builder.append("]");
        return builder.toString();
    }
}
