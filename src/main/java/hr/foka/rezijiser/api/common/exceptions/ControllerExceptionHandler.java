package hr.foka.rezijiser.api.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hr.foka.rezijiser.api.common.resources.MessageResource;
import hr.foka.rezijiser.api.common.resources.MessageResourceAssembler;
import hr.foka.rezijiser.api.common.resources.MessageResource.Type;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageResourceAssembler messageAssembler;

    public ControllerExceptionHandler(MessageResourceAssembler messageResourceAssembler) {
        this.messageAssembler = messageResourceAssembler;
    }

    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<MessageResource> missingDataExceptionHandler(MissingDataException e) {
        return new ResponseEntity<>(messageAssembler.assembleMessage(e.getMessage(), Type.ERROR), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageResource> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return new ResponseEntity<>(messageAssembler.assembleMessage(e.getMessage(), Type.ERROR), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AuthenticationException e) {
        return new ResponseEntity<>(messageAssembler.assembleMessage(e.getMessage(), Type.ERROR), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(messageAssembler.assembleMessage(e.getMessage(), Type.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
