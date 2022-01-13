package hr.foka.rezijiser.api.export.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.export.resource.ExportRequestResource;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;
import hr.foka.rezijiser.services.export.excel.ExcelBillExporter;
import hr.foka.rezijiser.services.export.excel.ExcelExporter;
import hr.foka.rezijiser.services.export.pdf.PdfBillExporter;
import hr.foka.rezijiser.services.export.pdf.PdfExporter;
import hr.foka.rezijiser.services.export.resource.ExportResource;

@Service
public class ExportServiceImpl implements ExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportServiceImpl.class);

    private ExcelExporter<Bill> excelExporter;
    private PdfExporter<Bill> pdfExporter;
    private BillFilteringService filterService;
    private BillRepository repository;
    private UserFilteringService userFilterService;
    private LocalDateConverter dateParser;

    public ExportServiceImpl(
        ExcelExporter<Bill> excelExporter,
        PdfExporter<Bill> pdfExporter,
        BillFilteringService filterService,
        BillRepository repository,
        UserFilteringService userFilterService,
        LocalDateConverter dateParser
    ){
        this.excelExporter = excelExporter;
        this.pdfExporter = pdfExporter;
        this.filterService = filterService;
        this.repository = repository;
        this.userFilterService = userFilterService;
        this.dateParser = dateParser;
    }

    @Override
    public ResponseEntity<?> requestExport(User user, ExportRequestResource request) throws IOException {
        LOGGER.info("Exporting bills from {} to {}", request.getStartingDate(), request.getEndingDate());

        BooleanExpression filter = userFilterService.filterForUser(user);
        filter = filter.and(filterService.filterByPaydayBetween(dateParser.convert(request.getStartingDate()), dateParser.convert(request.getEndingDate())));

        ExportResource<Bill> exportResource = null;
        byte[] exportData;
        HttpHeaders headers = new HttpHeaders();

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){

            switch(request.getExportType()){
                case EXCEL:
                    exportResource = toExcelExportResource(request);
                    exportResource.setData(toCollection(repository.findAll(filter)));
                    excelExporter.exportToOutputStream(stream, exportResource);
                    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx");
                    break;
                case PDF:
                    exportResource = toPdfExportResource(request);
                    exportResource.setData(toCollection(repository.findAll(filter)));
                    pdfExporter.exportToOutputStream(stream, exportResource);
                    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
                    break;
            }
            
            exportData = stream.toByteArray();
        } catch(IOException e){
            LOGGER.error("Could not export bills.", e);
            throw e;
        }

        LOGGER.info("Export successful.");
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(exportData.length)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(exportData);

    }

    private Collection<Bill> toCollection(Iterable<Bill> objects){
        
        List<Bill> bills = new ArrayList<>();

        for(Bill bill : objects){
            bills.add(bill);
        }

        return bills;

    }
    
    private ExportResource<Bill> toExcelExportResource(ExportRequestResource resource){

        ExportResource<Bill> export = new ExportResource<>(); 

        Map<String, Object> metadata = new HashMap<>();
        metadata.put(ExcelBillExporter.KEY_STARTING_DATE, dateParser.convert(resource.getStartingDate()));
        metadata.put(ExcelBillExporter.KEY_ENDING_DATE, dateParser.convert(resource.getEndingDate()));
        export.setMetadata(metadata);

        return export;

    }

    private ExportResource<Bill> toPdfExportResource(ExportRequestResource resource){

        ExportResource<Bill> export = new ExportResource<>(); 

        Map<String, Object> metadata = new HashMap<>();
        metadata.put(PdfBillExporter.KEY_STARTING_DATE, dateParser.convert(resource.getStartingDate()));
        metadata.put(PdfBillExporter.KEY_ENDING_DATE, dateParser.convert(resource.getEndingDate()));
        export.setMetadata(metadata);

        return export;

    }

}
