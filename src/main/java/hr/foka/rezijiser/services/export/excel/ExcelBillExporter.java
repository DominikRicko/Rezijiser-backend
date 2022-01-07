package hr.foka.rezijiser.services.export.excel;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.persistence.domain.Bill;

@Service
public class ExcelBillExporter extends AbstractExcelExporter<Bill>{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBillExporter.class);

    @Override
    protected Boolean _export(Sheet sheet, Collection<Bill> objects) {
        return null;
    }

}
