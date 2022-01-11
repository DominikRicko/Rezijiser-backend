package hr.foka.rezijiser.api.export.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import hr.foka.rezijiser.api.export.resource.ExportRequestResource;
import hr.foka.rezijiser.persistence.domain.User;

public interface ExportService {

    byte[] requestExport(User user, ExportRequestResource request, HttpServletResponse response) throws IOException;
    
}
