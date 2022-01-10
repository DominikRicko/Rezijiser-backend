package hr.foka.rezijiser.services.export.interfaces;

import org.apache.poi.ss.usermodel.Workbook;

import hr.foka.rezijiser.services.export.resource.ExportResource;

public interface ExcelExporter<T> {
    
    Boolean exportToWorkbook(Workbook workbook, ExportResource<T> objects);

}
