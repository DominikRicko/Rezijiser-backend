package hr.foka.rezijiser.services.export.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.foka.rezijiser.services.export.interfaces.ExcelExporter;
import hr.foka.rezijiser.services.export.interfaces.FileExporter;
import hr.foka.rezijiser.services.export.interfaces.StreamExporter;

public abstract class AbstractExcelExporter<T> implements ExcelExporter<T>, FileExporter<T>, StreamExporter<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExcelExporter.class);
    
    @Override
    public Boolean exportToFile(String filepath, Collection<T> objects) {
        LOGGER.info("Attempting to export {} objects into Excel file {}.", objects.size(), filepath);
        File file = new File(filepath);
        Boolean result = _exportToFile(file, objects);
        if(result) LOGGER.info("Objects exported into Excel file.");
        return result;
    }

    @Override
    public Boolean exportToFile(File file, Collection<T> objects) {
        LOGGER.info("Attempting to export {} objects into Excel file {}.", objects.size(), file.getName());
        Boolean result = _exportToFile(file, objects);
        if(result) LOGGER.info("Objects exported into Excel file.");
        return result;
    }

    @Override
    public Boolean exportToOutputStream(OutputStream stream, Collection<T> objects) {
        LOGGER.info("Attempting to export {} objects into output stream.", objects.size());
        Boolean result = _exportToOutputStream(stream, objects);
        if(result) LOGGER.info("Objects exported into output stream.");
        return result;
    }

    @Override
    public Boolean exportToWorkbook(Workbook workbook, Collection<T> objects) {
        LOGGER.info("Attempting to export {} objects into Excel Workbook.", objects.size());
        Boolean result = _exportToWorkbook(workbook, objects);
        if(result) LOGGER.info("Objects exported into Excel workbook.");
        return result;
    }

    @Override
    public Boolean exportToSheet(Sheet sheet, Collection<T> objects) {
        LOGGER.info("Attempting to export {} objects into Excel sheet.", objects.size());
        Boolean result = _exportToSheet(sheet, objects);
        if(result) LOGGER.info("Objects exported into Excel sheet.");
        return result;
    }

    protected Boolean _exportToFile(File file, Collection<T> objects){
        try(OutputStream stream = new FileOutputStream(file)){
            return _exportToOutputStream(stream, objects);
        } catch(IOException e){
            LOGGER.error("Could not close stream", e);
            return false;
        }
    }

    protected Boolean _exportToOutputStream(OutputStream stream, Collection<T> objects){
        try(Workbook workbook = WorkbookFactory.create(true)){
            Boolean result = _exportToWorkbook(workbook, objects);
            workbook.write(stream);
            return result;
        } catch(IOException e){
            LOGGER.error("Could not open/close/write to workbook", e);
            return false;
        }
    }

    protected Boolean _exportToWorkbook(Workbook workbook, Collection<T> objects){
       return _exportToSheet(workbook.createSheet(), objects);
    }

    protected Boolean _exportToSheet(Sheet sheet, Collection<T> objects){
        return _export(sheet, objects);
    }

    protected abstract Boolean _export(Sheet sheet, Collection<T> objects);

}
