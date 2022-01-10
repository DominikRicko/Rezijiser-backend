package hr.foka.rezijiser.services.export.interfaces;

import java.io.Writer;

import hr.foka.rezijiser.services.export.resource.ExportResource;

public interface WriterExporter<T> {

    Boolean formatToWriter(Writer writer, ExportResource<T> objects);
    
}
