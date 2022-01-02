package hr.foka.rezijiser.api.common.resources;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.domain.Sort;

import hr.foka.rezijiser.api.common.resources.ResourceFilter.TargetColumn;

public class ResourcePage {

    @JsonProperty("pageNumber")
    private int pageNumber = 0;

    @JsonProperty("pageSize")
    private int pageSize = 10;

    @JsonProperty("sortDirection")
    private Sort.Direction sortDirection;

    @JsonProperty("sortBy")
    private TargetColumn sortBy = TargetColumn.PAYDAY;

    @JsonProperty("filters")
    private Collection<ResourceFilter> filters;

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

    public Collection<ResourceFilter> getFilters() {
        return this.filters;
    }

    public void setFilters(Collection<ResourceFilter> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(ResourcePage.class.getName());
        builder.append(" [");
        builder.append("pageNumber=").append(pageNumber);
        builder.append(", pageSize=").append(pageSize);
        builder.append(", sortDirection=").append(sortDirection);
        builder.append(", sortBy=").append(sortBy);
        builder.append(", filters=").append(filters);
        builder.append("]");
        return builder.toString();
    }
}