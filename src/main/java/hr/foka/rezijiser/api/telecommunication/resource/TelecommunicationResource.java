package hr.foka.rezijiser.api.telecommunication.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hr.foka.rezijiser.api.common.resources.CommonResource;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TelecommunicationResource extends CommonResource {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(TelecommunicationResource.class.getName());
        builder.append(" [");
        builder.append("id=").append(id);
        builder.append(",cost=").append(cost);
        builder.append(",payday=").append(payday);
        builder.append(",datePaid=").append(datePaid);
        builder.append("]");
        return builder.toString();
    }
}