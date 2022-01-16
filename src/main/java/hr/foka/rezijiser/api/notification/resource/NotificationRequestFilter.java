package hr.foka.rezijiser.api.notification.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationRequestFilter {
    
    public static enum TargetColumn{
        @JsonProperty("type") TYPE("level"),
        @JsonProperty("title") TITLE("title"),
        @JsonProperty("message") MESSAGE("message"),
        @JsonProperty("timeCreated") TIME_CREATED("time_created"),
        @JsonProperty("checked") CHECKED("checked");

        TargetColumn(String columnName){
            this.columnName = columnName;
        }
        
        public String getColumnName(){
            return this.columnName;
        }

        private final String columnName;
    }

    public static enum FilterOperation{
        LT, GT, EQ, REGEX
    }

    @JsonProperty("operation")
    private FilterOperation op;

    @JsonProperty("column")
    private TargetColumn col;

    @JsonProperty("value")
    private String val;


    public FilterOperation getOp() {
        return this.op;
    }

    public void setOp(FilterOperation op) {
        this.op = op;
    }

    public TargetColumn getCol() {
        return this.col;
    }

    public void setCol(TargetColumn col) {
        this.col = col;
    }

    public String getVal() {
        return this.val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(NotificationRequestFilter.class.getSimpleName());
        builder.append(" [");
        builder.append("op=").append(op);
        builder.append(", col=").append(col);
        builder.append(", val=").append(val);
        builder.append("]");
        return builder.toString();
    }

}
