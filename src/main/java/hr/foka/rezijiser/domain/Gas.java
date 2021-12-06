package hr.foka.rezijiser.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
public class Gas extends CommonTable{
    
    @Column(name = "id_user")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "payday")
    private LocalDate payday;

    @Column(name = "date_paid")
    private LocalDate datePaid;

    @Column(name ="counter")
    private BigDecimal counter;

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

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", cost='" + getCost() + "'" +
            ", payday='" + getPayday() + "'" +
            ", datePaid='" + getDatePaid() + "'" +
            ", counter='" + getCounter() + "'" +
            "}";
    }

}