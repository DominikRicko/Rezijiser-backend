package hr.foka.rezijiser.api.reservation.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hr.foka.rezijiser.api.common.resources.CommonResource;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationResource extends CommonResource {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(ReservationResource.class.getName());
        builder.append(" [");
        builder.append("id=").append(id);
        builder.append(",cost=").append(cost);
        builder.append(",payday=").append(payday);
        builder.append(",datePaid=").append(datePaid);
        builder.append("]");
        return builder.toString();
    }
}