package hr.foka.rezijiser.services.export.interfaces;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelExporter<T> {
    
    Boolean exportToWorkbook(Workbook workbook, Collection<T> objects);

    Boolean exportToSheet(Sheet sheet, Collection<T> objects);

}
