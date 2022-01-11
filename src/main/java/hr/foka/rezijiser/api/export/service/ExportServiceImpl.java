package hr.foka.rezijiser.api.export.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.export.resource.ExportRequestResource;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;
import hr.foka.rezijiser.services.export.excel.ExcelBillExporter;
import hr.foka.rezijiser.services.export.resource.ExportResource;

@Service
public class ExportServiceImpl implements ExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportServiceImpl.class);

    private ExcelBillExporter exporter;
    private BillFilteringService filterService;
    private BillRepository repository;
    private UserFilteringService userFilterService;
    private LocalDateConverter dateParser;

    public ExportServiceImpl(
        ExcelBillExporter exporter,
        BillFilteringService filterService,
        BillRepository repository,
        UserFilteringService userFilterService,
        LocalDateConverter dateParser
    ){
        this.exporter = exporter;
        this.filterService = filterService;
        this.repository = repository;
        this.userFilterService = userFilterService;
        this.dateParser = dateParser;
    }

    @Override
    public byte[] requestExport(User user, ExportRequestResource request, HttpServletResponse response) throws IOException {
        LOGGER.info("Exporting bills from {} to {}", request.getStartingDate(), request.getEndingDate());

        BooleanExpression filter = userFilterService.filterForUser(user);
        filter = filter.and(filterService.filterByPaydayBetween(dateParser.convert(request.getStartingDate()), dateParser.convert(request.getEndingDate())));

        ExportResource<Bill> exportResource = toExportResource(request);
        exportResource.setData(toCollection(repository.findAll(filter)));

        byte[] excelData;

        response.setContentType("application/xlsx");

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){
            exporter.exportToOutputStream(stream, exportResource);
            excelData = stream.toByteArray();
        } catch(IOException e){
            LOGGER.error("Could not export bills.", e);
            throw e;
        }

        return excelData;

    }

    private Collection<Bill> toCollection(Iterable<Bill> objects){
        
        List<Bill> bills = new ArrayList<>();

        for(Bill bill : objects){
            bills.add(bill);
        }

        return bills;

    }
    
    private ExportResource<Bill> toExportResource(ExportRequestResource resource){

        ExportResource<Bill> export = new ExportResource<>(); 

        Map<String, Object> metadata = new HashMap<>();
        metadata.put(ExcelBillExporter.KEY_STARTING_DATE, dateParser.convert(resource.getStartingDate()));
        metadata.put(ExcelBillExporter.KEY_ENDING_DATE, dateParser.convert(resource.getEndingDate()));
        export.setMetadata(metadata);

        return export;

    }

}
