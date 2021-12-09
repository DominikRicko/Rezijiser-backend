package hr.foka.rezijiser.domain;

import java.time.ZonedDateTime;

public abstract class CommonTable {    

    private ZonedDateTime timeCreated;

    private ZonedDateTime timeModified;

    public ZonedDateTime getTimeCreated() {
        return timeCreated;
    }
    
    public ZonedDateTime getTimeModified() {
        return timeModified;
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
        builder.append("[timeCreated").append(timeCreated);
        builder.append(", timeModified").append(timeModified);
        builder.append("]");
        return builder.toString();
    }
}
