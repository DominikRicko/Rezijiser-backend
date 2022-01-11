package hr.foka.rezijiser.api.export.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.foka.rezijiser.api.export.resource.ExportRequestResource;
import hr.foka.rezijiser.api.export.service.ExportService;
import hr.foka.rezijiser.persistence.domain.User;

@RestController
@RequestMapping(path = "/e/api/v1/export")
public class ExportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    private ExportService service;

    public ExportController(ExportService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public byte[] requestExport(@AuthenticationPrincipal User user, @RequestBody ExportRequestResource request, HttpServletResponse response)
            throws IOException {
        LOGGER.info("Received request to export data.");
        byte[] data = service.requestExport(user, request, response);
        LOGGER.info("Export successful");
        return data;

    }

}
