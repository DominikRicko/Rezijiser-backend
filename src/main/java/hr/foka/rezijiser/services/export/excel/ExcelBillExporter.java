package hr.foka.rezijiser.services.export.excel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.services.export.resource.ExportResource;

@Service
public class ExcelBillExporter extends AbstractExcelExporter<Bill> {

    public static final String KEY_STARTING_DATE = "excelExportStartingDate";
    public static final String KEY_ENDING_DATE = "excelExportEndingDate";

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBillExporter.class);

    @Override
    protected Boolean export(Workbook workbook, ExportResource<Bill> objects) {

        Collection<Bill> data = objects.getData().stream().sorted(this::sortCollectionByPayday)
                .collect(Collectors.toList());
        objects.setData(data);

        Sheet generalSheet = workbook.createSheet("Izvoz");
        Sheet powerSheet = workbook.createSheet("Struja");
        Sheet waterSheet = workbook.createSheet("Voda");
        Sheet gasSheet = workbook.createSheet("Gas");
        Sheet reservationSheet = workbook.createSheet("Pričuva");
        Sheet trashSheet = workbook.createSheet("Smeće");
        Sheet communalSheet = workbook.createSheet("Komunalac");
        Sheet hrtSheet = workbook.createSheet("Hrt");
        Sheet telecommunicationSheet = workbook.createSheet("Telekom");

        LOGGER.info("Generating report sheet.");
        exportReportToSheet(generalSheet, objects);

        LOGGER.info("Data dumping into other sheets.");
        exportBillsToSheet(powerSheet,
                data.stream().filter(it -> it.getType() == Type.POWER).collect(Collectors.toList()));
        exportBillsToSheet(waterSheet,
                data.stream().filter(it -> it.getType() == Type.WATER).collect(Collectors.toList()));
        exportBillsToSheet(gasSheet,
                data.stream().filter(it -> it.getType() == Type.GAS).collect(Collectors.toList()));
        exportBillsToSheet(reservationSheet,
                data.stream().filter(it -> it.getType() == Type.RESERVATION).collect(Collectors.toList()));
        exportBillsToSheet(trashSheet,
                data.stream().filter(it -> it.getType() == Type.TRASH).collect(Collectors.toList()));
        exportBillsToSheet(communalSheet,
                data.stream().filter(it -> it.getType() == Type.COMMUNAL).collect(Collectors.toList()));
        exportBillsToSheet(hrtSheet,
                data.stream().filter(it -> it.getType() == Type.HRT).collect(Collectors.toList()));
        exportBillsToSheet(telecommunicationSheet,
                data.stream().filter(it -> it.getType() == Type.TELECOMMUNICATION).collect(Collectors.toList()));

        return true;
    }

    private void exportBillsToSheet(Sheet sheet, Collection<Bill> bills) {

        Row header = sheet.createRow(0);
        int rowCounter = 0;

        header.createCell(0).setCellValue("Datum dospijeća");
        header.createCell(1).setCellValue("Datum plaćanja");
        header.createCell(2).setCellValue("Iznos plaćanja");
        header.createCell(3).setCellValue("Potrošnja");

        for (Bill bill : bills) {
            Row dataRow = sheet.createRow(++rowCounter);
            dataRow.createCell(0).setCellValue(bill.getPayday().toString());
            if (bill.getDatePaid() != null)
                dataRow.createCell(1).setCellValue(bill.getDatePaid().toString());
            else
                dataRow.createCell(1).setCellValue("NEPLAĆENO");
            dataRow.createCell(2).setCellValue(bill.getCost().toString());
            
            if(bill.getSpent() != null)
                dataRow.createCell(3).setCellValue(bill.getSpent().toString());
        }

        for(int i = 0; i < 4; i++){
            sheet.autoSizeColumn(i);
        }

    }

    private void exportReportToSheet(Sheet sheet, ExportResource<Bill> bills) {

        List<BigDecimal> minimal = new ArrayList<>(8);
        List<BigDecimal> maximal = new ArrayList<>(8);
        List<BigDecimal> total = new ArrayList<>(8);

        findStats(bills.getData(), total, minimal, maximal);

        Map<String, Object> metadata = bills.getMetadata();
        LocalDate startingDate = (LocalDate) metadata.get(KEY_STARTING_DATE);
        LocalDate endingDate = (LocalDate) metadata.get(KEY_ENDING_DATE);

        Row row = sheet.createRow(0);

        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        row.createCell(0).setCellValue(String.format("Izvještaj o režijama u vremenskom periodu od %s do %s",
                startingDate.toString(), endingDate.toString()));

        row = sheet.createRow(2);
        row.createCell(1).setCellValue("Struja");
        row.createCell(2).setCellValue("Voda");
        row.createCell(3).setCellValue("Plin");
        row.createCell(4).setCellValue("Pričuva");
        row.createCell(5).setCellValue("Smeće");
        row.createCell(6).setCellValue("Komunalac");
        row.createCell(7).setCellValue("Hrt");
        row.createCell(8).setCellValue("Telekom");

        row = sheet.createRow(3);
        row.createCell(0).setCellValue("Ukupna potrošnja");
        for(int i = 0; i < 8; i++){
            if(total.get(i) != null)
                row.createCell(i + 1).setCellValue(total.get(i).toString());
        }

        row = sheet.createRow(4);
        row.createCell(0).setCellValue("Najveći iznos");
        for(int i = 0; i < 8; i++){
            if(maximal.get(i) != null)
                row.createCell(i + 1).setCellValue(maximal.get(i).toString());
        }

        row = sheet.createRow(5);
        row.createCell(0).setCellValue("Najmanji iznos");
        for(int i = 0; i < 8; i++){
            if(minimal.get(i) != null)
                row.createCell(i + 1).setCellValue(minimal.get(i).toString());
        }

        row = sheet.createRow(6);
        row.createCell(0).setCellValue("Ukupno");
        row.createCell(1).setCellValue(getTotalInCollection(total).toString());

        for(int i = 0; i < 9; i++){
            sheet.autoSizeColumn(i);
        }

    }

    private int sortCollectionByPayday(Bill bill1, Bill bill2) {
        return bill1.getPayday().compareTo(bill2.getPayday());
    }

    private void findStats(Collection<Bill> bills, List<BigDecimal> total, List<BigDecimal> minimal,
            List<BigDecimal> maximal) {

        HashMap<Bill.Type, BigDecimal> minimalMap = new HashMap<>();
        HashMap<Bill.Type, BigDecimal> maximalMap = new HashMap<>();
        HashMap<Bill.Type, BigDecimal> sumMap = new HashMap<>();

        for (Bill.Type type : Bill.Type.values()) {
            sumMap.put(type, BigDecimal.ZERO);
            minimal.add(null);
            maximal.add(null);
            total.add(null);
        }

        for (Bill bill : bills) {

            if(minimalMap.get(bill.getType()) != null){
                if (minimalMap.get(bill.getType()).compareTo(bill.getCost()) > 0){
                    minimalMap.put(bill.getType(), bill.getCost());
                }
            } else {
                minimalMap.put(bill.getType(), bill.getCost());
            }
            
            if(maximalMap.get(bill.getType()) != null){
                if (maximalMap.get(bill.getType()).compareTo(bill.getCost()) < 0){
                    maximalMap.put(bill.getType(), bill.getCost());
                }
            } else {
                maximalMap.put(bill.getType(), bill.getCost());
            }

            sumMap.put(bill.getType(), sumMap.get(bill.getType()).add(bill.getCost()));
            
        }

        for(Bill.Type type : Bill.Type.values()){

            int switchIndex = -1;
            switch (type) {
                case POWER: switchIndex = 0; break;
                case WATER: switchIndex = 1; break;
                case GAS: switchIndex = 2; break;
                case RESERVATION: switchIndex = 3; break; 
                case TRASH: switchIndex = 4; break;
                case COMMUNAL: switchIndex = 5; break;
                case HRT: switchIndex = 6; break;
                case TELECOMMUNICATION: switchIndex = 7; break;
                default: continue;
            }
        
            maximal.set(switchIndex, maximalMap.get(type));
            minimal.set(switchIndex, minimalMap.get(type));
            total.set(switchIndex, sumMap.get(type));

        }

    }

    private BigDecimal getTotalInCollection(Collection<BigDecimal> data){

        return data.stream().reduce((i, acc) -> acc.add(i)).get();

    }

}
