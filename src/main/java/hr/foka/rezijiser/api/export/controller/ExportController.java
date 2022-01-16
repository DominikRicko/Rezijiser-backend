package hr.foka.rezijiser.api.export.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.foka.rezijiser.api.common.resources.MessageResource;
import hr.foka.rezijiser.api.common.resources.MessageResourceAssembler;
import hr.foka.rezijiser.api.common.resources.MessageResource.Type;
import hr.foka.rezijiser.api.export.resource.ExportRequestResource;
import hr.foka.rezijiser.api.export.service.ExportService;
import hr.foka.rezijiser.persistence.domain.User;

@RestController
@RequestMapping(path = "/e/api/v1/export")
public class ExportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    private ExportService service;
    private MessageResourceAssembler messageAssembler;

    public ExportController(ExportService service, MessageResourceAssembler messageAssembler) {
        this.service = service;
        this.messageAssembler = messageAssembler;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> requestExport(@AuthenticationPrincipal User user,
            @RequestBody ExportRequestResource request)
            throws IOException {
        LOGGER.info("Received request to export data.");
        return service.requestExport(user, request);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<MessageResource> IOExceptionHandler(IOException e) {
        return new ResponseEntity<>(messageAssembler.assembleMessage("Pogreška pri izvozu u datoteku.", Type.ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
