package hr.foka.rezijiser.api.common.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CommonResource {

    @JsonProperty("identificator")
    protected Integer id;

    @JsonProperty("cost")
    protected String cost;

    @JsonProperty("payday")
    protected String payday;

    @JsonProperty("datePaid")
    protected String datePaid;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPayday() {
        return this.payday;
    }

    public void setPayday(String payday) {
        this.payday = payday;
    }

    public String getDatePaid() {
        return this.datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonResource.class.getName());
        builder.append(" [");
        builder.append("id=").append(id);
        builder.append(",cost=").append(cost);
        builder.append(",payday=").append(payday);
        builder.append(",datePaid=").append(datePaid);
        builder.append("]");
        return builder.toString();
    }

}