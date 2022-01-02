package hr.foka.rezijiser.api.notification.resource;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.domain.Sort;

import hr.foka.rezijiser.api.notification.resource.NotificationRequestFilter.TargetColumn;

public class NotificationRequest {
    
    @JsonProperty("pageNumber")
    private int pageNumber = 0;

    @JsonProperty("pageSize")
    private int pageSize = 10;

    @JsonProperty("sortDirection")
    private Sort.Direction sortDirection = Sort.Direction.DESC;

    @JsonProperty("sortBy")
    private TargetColumn sortBy = TargetColumn.TIME_CREATED;

    @JsonProperty("filters")
    private Collection<NotificationRequestFilter> filters;

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Sort.Direction getSortDirection() {
        return this.sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public TargetColumn getSortBy() {
        return this.sortBy;
    }

    public void setSortBy(TargetColumn sortBy) {
        this.sortBy = sortBy;
    }


    public Collection<NotificationRequestFilter> getFilters() {
        return this.filters;
    }

    public void setFilters(Collection<NotificationRequestFilter> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(NotificationResource.class.getName());
        builder.append(" [");
        builder.append("pageNumber=").append(pageNumber);
        builder.append(", pageSize=").append(pageSize);
        builder.append(", sortBy=").append(sortBy);
        builder.append(", sortDirection=").append(sortDirection);
        builder.append(", filters=").append(filters);
        builder.append("]");
        return builder.toString();
    }

}
