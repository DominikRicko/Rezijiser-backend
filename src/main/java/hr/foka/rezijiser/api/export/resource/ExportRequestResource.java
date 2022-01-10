package hr.foka.rezijiser.api.export.resource;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExportRequestResource {
    
    public static enum ExportType{
        @JsonProperty("excel") EXCEL
    }

    @JsonProperty("startingDate")
    private LocalDate startingDate;

    @JsonProperty("endingDate")
    private LocalDate endingDate;

    @JsonProperty("exportType")
    private ExportType exportType;

    public LocalDate getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return this.endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public ExportType getExportType() {
        return this.exportType;
    }

    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder(ExportRequestResource.class.getName());
        builder.append("[");
        builder.append("exportType=").append(exportType);
        builder.append("startingDate=").append(startingDate);
        builder.append("endingDate=").append(endingDate);
        builder.append("]");
        return builder.toString();
    }

}
