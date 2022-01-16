package hr.foka.rezijiser.api.common.exceptions;

public class MissingDataException extends RuntimeException{
    
    public MissingDataException(String dataName){
        super(String.format("Nedostaje podatak %s", dataName));
    }

    public MissingDataException(String... dataNames){
        super(buildMessage(dataNames));
    }

    private static String buildMessage(String... dataNames){
        StringBuilder builder = new StringBuilder("Nedostaju sljedeći podaci: ");

        for(String dataName : dataNames){
            builder.append(dataName).append(", ");
        }

        return builder.toString();

    }

}
