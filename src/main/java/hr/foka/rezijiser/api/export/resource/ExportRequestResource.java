package hr.foka.rezijiser.api.export.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExportRequestResource {
    
    public static enum ExportType{
        @JsonProperty("excel") EXCEL,
        @JsonProperty("pdf") PDF
    }

    @JsonProperty("startingDate")
    private String startingDate;

    @JsonProperty("endingDate")
    private String endingDate;

    @JsonProperty("exportType")
    private ExportType exportType;

    public String getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return this.endingDate;
    }

    public void setEndingDate(String endingDate) {
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
