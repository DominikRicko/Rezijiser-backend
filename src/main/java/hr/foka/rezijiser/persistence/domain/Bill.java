package hr.foka.rezijiser.persistence.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
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

//Reduced all tables into one to reduce the amount of code to be written for filtering.

@Entity
public class Bill {
    
    public static enum Type{
        POWER, WATER, GAS, RESERVATION, TRASH, COMMUNAL, HRT, TELECOMMUNICATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "spent")
    private BigDecimal spent;

    @Column(name = "payday")
    private LocalDate payday;

    @Column(name = "date_paid")
    private LocalDate datePaid;

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

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getSpent() {
        return this.spent;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
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
        StringBuilder builder = new StringBuilder(Bill.class.getName());
        builder.append(" [");
        builder.append("id=").append(id);
        builder.append(", user=").append(user);
        builder.append(", type=").append(type);
        builder.append(", cost=").append(cost);
        builder.append(", spent=").append(spent);
        builder.append(", payday=").append(payday);
        builder.append(", datePaid=").append(datePaid);
        builder.append(", timeCreater=").append(timeCreated);
        builder.append(", timeModified=").append(timeModified);
        builder.append("]");
        return builder.toString();
    }

}