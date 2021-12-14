package hr.foka.rezijiser.persistence.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "telecommunications")
public class Telecom extends CommonTable{

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

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", cost='" + getCost() + "'" +
            ", payday='" + getPayday() + "'" +
            ", datePaid='" + getDatePaid() + "'" +
            "}";
    }

}
