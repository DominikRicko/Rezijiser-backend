package hr.foka.rezijiser.api.common.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceFilter {
    
    public static enum FilterOperation {
        LT, //Less than
        GT, //Greater than
        EQ, //Equals
        PAID, //"true" for if paid, "false" for if not paid
    }

    public static enum TargetColumn{

        COST("cost"),
        PAYDAY("payday"),
        DATEPAID("date_paid"),
        COUNTER("counter");

        TargetColumn(String columnName){
            this.columnName = columnName;
        }
        
        public String getColumnName(){
            return this.columnName;
        }

        private final String columnName;

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
        StringBuilder builder = new StringBuilder(ResourceFilter.class.getName());
        builder.append(" [");
        builder.append("op=").append(op);
        builder.append(",col=").append(col);
        builder.append(",val=").append(val);
        builder.append("]");
        return builder.toString();
    }

}