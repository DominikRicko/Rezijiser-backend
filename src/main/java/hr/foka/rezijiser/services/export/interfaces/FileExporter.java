package hr.foka.rezijiser.services.export.interfaces;

import java.io.File;
import java.util.Collection;

public interface FileExporter<T> {

    Boolean exportToFile(String filepath, Collection<T> objects);

    Boolean exportToFile(File file, Collection<T> objects);

}
