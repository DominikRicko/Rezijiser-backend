package hr.foka.rezijiser.api.common.resources;

public class MessageResource {
    
    public static enum Type{
        INFO, ERROR, WARNING
    }

    private String message;
    private Type type;


    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder(MessageResource.class.getSimpleName());
        builder.append("[");
        builder.append("message=").append(message);
        builder.append(", type=").append(type);
        builder.append("]");
        return builder.toString();
    }
}
