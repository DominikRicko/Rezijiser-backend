package hr.foka.rezijiser.persistence.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hr.foka.rezijiser.persistence.service.ZonedDateTimeConverter;

@Entity
public class Power {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "payday")
    private LocalDate payday;

    @Column(name = "date_paid")
    private LocalDate datePaid;

    @Column(name = "counter")
    private BigDecimal counter;

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

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDate getPayday() {
        return this.payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public LocalDate getDatePaid() {
        return this.datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public BigDecimal getCounter() {
        return this.counter;
    }

    public void setCounter(BigDecimal counter) {
        this.counter = counter;
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
        StringBuilder builder = new StringBuilder(Power.class.getName());
        builder.append(" [");
        builder.append("id=").append(id);
        builder.append(",cost=").append(cost);
        builder.append(",payday=").append(payday);
        builder.append(",datePaid=").append(datePaid);
        builder.append(",counter=").append(counter);
        builder.append(",timeCreater=").append(timeCreated);
        builder.append(",timeModified=").append(timeModified);
        builder.append("]");
        return builder.toString();
    }

}