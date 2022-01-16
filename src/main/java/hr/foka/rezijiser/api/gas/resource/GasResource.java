package hr.foka.rezijiser.api.gas.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import hr.foka.rezijiser.api.common.resources.CommonResource;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GasResource extends CommonResource {

    @JsonProperty("counter")
    private String counter;

    public String getCounter() {
        return this.counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(GasResource.class.getSimpleName());
        builder.append(" [");
        builder.append("id=").append(id);
        builder.append(", cost=").append(cost);
        builder.append(", counter=").append(counter);
        builder.append(", payday=").append(payday);
        builder.append(", datePaid=").append(datePaid);
        builder.append("]");
        return builder.toString();
    }
}