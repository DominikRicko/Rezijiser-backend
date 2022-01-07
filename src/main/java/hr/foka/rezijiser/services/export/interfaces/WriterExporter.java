package hr.foka.rezijiser.services.export.interfaces;

import java.io.Writer;
import java.util.Collection;

public interface WriterExporter<T> {

    Boolean formatToWriter(Writer writer, Collection<T> objects);
    
}
