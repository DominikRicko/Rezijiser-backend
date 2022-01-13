package hr.foka.rezijiser.api.export.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import hr.foka.rezijiser.api.export.resource.ExportRequestResource;
import hr.foka.rezijiser.persistence.domain.User;

public interface ExportService {

    ResponseEntity<?> requestExport(User user, ExportRequestResource request) throws IOException;
    
}
