package hr.foka.rezijiser.services.export.interfaces;

import java.io.OutputStream;

import hr.foka.rezijiser.services.export.resource.ExportResource;

public interface StreamExporter<T> {
    
    Boolean exportToOutputStream(OutputStream stream, ExportResource<T> objects);

}
