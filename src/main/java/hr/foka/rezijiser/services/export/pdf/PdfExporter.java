package hr.foka.rezijiser.services.export.pdf;

import com.itextpdf.text.Document;

import hr.foka.rezijiser.services.export.interfaces.FileExporter;
import hr.foka.rezijiser.services.export.interfaces.StreamExporter;
import hr.foka.rezijiser.services.export.resource.ExportResource;

public interface PdfExporter<T> extends FileExporter<T>, StreamExporter<T>{
    
    Boolean exportToDocument(Document document, ExportResource<T> objects);

}
