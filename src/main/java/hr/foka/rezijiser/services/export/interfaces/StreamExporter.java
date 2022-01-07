package hr.foka.rezijiser.services.export.interfaces;

import java.io.OutputStream;
import java.util.Collection;

public interface StreamExporter<T> {
    
    Boolean exportToOutputStream(OutputStream stream, Collection<T> objects);

}
