package hr.foka.rezijiser.services.export.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.foka.rezijiser.services.export.resource.ExportResource;

public abstract class AbstractPdfExporter<T> implements PdfExporter<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPdfExporter.class);

    @Override
    public Boolean exportToFile(String filepath, ExportResource<T> objects) {
        LOGGER.info("Attempting to export {} objects into PDF file {}.", objects.getData().size(), filepath);
        File file = new File(filepath);
        Boolean result = _exportToFile(file, objects);
        if(result) LOGGER.info("Objects successfully exported into PDF file.");
        return result;
    }

    @Override
    public Boolean exportToFile(File file, ExportResource<T> objects) {
        LOGGER.info("Attempting to export {} objects into PDF file {}.", objects.getData().size(), file.getName());
        Boolean result = _exportToFile(file, objects);
        if(result) LOGGER.info("Objects successfully exported into PDF file.");
        return result;
    }

    @Override
    public Boolean exportToOutputStream(OutputStream stream, ExportResource<T> objects) {
        LOGGER.info("Attempting to export {} objects into output stream.", objects.getData().size());
        Boolean result = _exportToOutputStream(stream, objects);
        if(result) LOGGER.info("Objects successfully exported into output stream.");
        return result;
    }

    @Override
    public Boolean exportToDocument(Document document, ExportResource<T> objects) {
        LOGGER.info("Attempting to export {} objects into PDF document.", objects.getData().size());
        Boolean result = _exportToDocument(document, objects);
        if(result) LOGGER.info("Objects successfully exported into document.");
        return result;
    }

    protected Boolean _exportToFile(File file, ExportResource<T> objects){
        try(OutputStream stream = new FileOutputStream(file)){
            return _exportToOutputStream(stream, objects);
        } catch(IOException e){
            LOGGER.error("Could not close stream", e);
            return false;
        }
    }

    protected Boolean _exportToOutputStream(OutputStream stream, ExportResource<T> objects){
        Document document = new Document();
        PdfWriter writer = null;
        Boolean result = false;

        try {
            writer = PdfWriter.getInstance(document, stream);
            result = _exportToDocument(document, objects);
            document.close();
        } catch( DocumentException e){
            LOGGER.error("Could not open PdfWriter instance", e);
            return false;
        } finally{
            if(writer != null){
                writer.close();
            }
        }

        return result;
    }

    protected Boolean _exportToDocument(Document document, ExportResource<T> objects){
        try{
            return export(document, objects);
        } catch (DocumentException e){
            LOGGER.error("Could not export data to PDF document", e);
            return false;
        }
        
    }
    
    protected abstract Boolean export(Document document, ExportResource<T> objects) throws DocumentException;

}
