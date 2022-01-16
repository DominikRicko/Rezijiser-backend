package hr.foka.rezijiser.api.common.exceptions;

public class EntityNotFoundException extends RuntimeException{
    
    public EntityNotFoundException(Integer id){
        super(String.format("Podatak sa identifikatorom %d nije pronađen.", id));
    }
}
