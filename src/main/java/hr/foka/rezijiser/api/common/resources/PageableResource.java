package hr.foka.rezijiser.api.common.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableResource implements Pageable {

    @JsonProperty(value = "page")
    private Integer page;

    @JsonProperty(value = "size")
    private Integer size;

    @JsonProperty(value = "sort")
    private SortDirection sort;

    private enum SortDirection{
        ASC, DESC
    }

    public PageableResource(Integer page, Integer size){
        this.page = page;
        this.size = size;
    }

    @Override
    public Pageable first() {
        return new PageableResource(0, this.size);
    }

    @Override
    public long getOffset() {
        return page*size;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public Sort getSort() {

        switch(this.sort){
            case ASC:{
                
            }
            case DESC:{

            }
        }
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }

    @Override
    public Pageable next() {
        return new PageableResource(this.page + 1, this.size);
    }

    @Override
    public Pageable previousOrFirst() {
        if(this.page == 0){
            return this;
        } else if(this.page > 0 ){
            return new PageableResource(this.page - 1, this.size);
        } else {
            return new PageableResource(0, this.size);
        }
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new PageableResource(pageNumber, this.size);
    }

}
