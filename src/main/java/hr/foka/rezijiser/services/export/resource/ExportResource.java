package hr.foka.rezijiser.services.export.resource;

import java.util.Collection;
import java.util.Map;

//generic class for passing data and information to whatever exporter implementation.

public class ExportResource<T> {

    private Collection<T> data;
    private Map<String, Object> metadata;

    public Collection<T> getData() {
        return this.data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public Map<String,Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String,Object> metadata) {
        this.metadata = metadata;
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder(ExportResource.class.getName());
        builder.append("[");
        builder.append("metadata=").append(metadata);
        builder.append(", data.count=").append(data.size());
        builder.append("]");
        return builder.toString();

    }
}
