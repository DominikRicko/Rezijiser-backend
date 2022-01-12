package hr.foka.rezijiser.services.export.excel;

import org.apache.poi.ss.usermodel.Workbook;

import hr.foka.rezijiser.services.export.interfaces.FileExporter;
import hr.foka.rezijiser.services.export.interfaces.StreamExporter;
import hr.foka.rezijiser.services.export.resource.ExportResource;

public interface ExcelExporter<T> extends FileExporter<T>, StreamExporter<T> {
    
    Boolean exportToWorkbook(Workbook workbook, ExportResource<T> objects);

}
