package hr.foka.rezijiser.services.export.interfaces;

import java.io.File;

import hr.foka.rezijiser.services.export.resource.ExportResource;

public interface FileExporter<T> {

    Boolean exportToFile(String filepath, ExportResource<T> objects);

    Boolean exportToFile(File file, ExportResource<T> objects);

}
