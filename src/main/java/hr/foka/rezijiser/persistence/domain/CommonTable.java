package hr.foka.rezijiser.persistence.domain;

import java.time.ZonedDateTime;

import javax.persistence.Convert;

import hr.foka.rezijiser.persistence.service.ZonedDateTimeConverter;

public abstract class CommonTable {    

    @Convert(converter = ZonedDateTimeConverter.class) 
    protected ZonedDateTime timeCreated;

    @Convert(converter = ZonedDateTimeConverter.class) 
    protected ZonedDateTime timeModified;

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
