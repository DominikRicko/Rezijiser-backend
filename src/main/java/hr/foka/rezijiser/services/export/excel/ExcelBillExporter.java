package hr.foka.rezijiser.services.export.excel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
            dataRow.createCell(0).setCellValue(bill.getPayday());
            if (bill.getDatePaid() != null)
                dataRow.createCell(1).setCellValue(bill.getDatePaid());
            else
                dataRow.createCell(1).setCellValue("NEPLAĆENO");
            dataRow.createCell(2).setCellValue(bill.getCost().toString());
            dataRow.createCell(3).setCellValue(bill.getSpent().toString());
        }

    }

    private void exportReportToSheet(Sheet sheet, ExportResource<Bill> bills) {

        List<BigDecimal> minimal = new ArrayList<>();
        List<BigDecimal> maximal = new ArrayList<>();
        List<BigDecimal> total = new ArrayList<>();

        findStats(bills.getData(), total, minimal, maximal);

        Map<String, Object> metadata = bills.getMetadata();
        LocalDate startingDate = (LocalDate) metadata.get(KEY_STARTING_DATE);
        LocalDate endingDate = (LocalDate) metadata.get(KEY_ENDING_DATE);

        Row row = sheet.createRow(0);

        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));

        row.createCell(0).setCellValue(String.format("Izvještaj o režijama u vrmeenskom periodu od {} do {}",
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
            row.createCell(i + 1).setCellValue(total.get(i).toString());
        }

        row = sheet.createRow(4);
        row.createCell(0).setCellValue("Najveći iznos");
        for(int i = 0; i < 8; i++){
            row.createCell(i + 1).setCellValue(maximal.get(i).toString());
        }

        row = sheet.createRow(5);
        row.createCell(0).setCellValue("Najmanji iznos");
        for(int i = 0; i < 8; i++){
            row.createCell(i + 1).setCellValue(minimal.get(i).toString());
        }

        row = sheet.createRow(6);
        row.createCell(0).setCellValue("Ukupno");
        row.createCell(1).setCellValue(getTotalInCollection(total).toString());

    }

    private int sortCollectionByPayday(Bill bill1, Bill bill2) {
        return bill1.getPayday().compareTo(bill2.getPayday());
    }

    private void findStats(Collection<Bill> bills, List<BigDecimal> total, List<BigDecimal> minimal,
            List<BigDecimal> maximal) {

        for (int i = 0; i < 8; i++) {
            total.set(i, new BigDecimal("0.00"));
            minimal.set(i, new BigDecimal("0.00"));
            maximal.set(i, new BigDecimal("0.00"));
        }

        for (Bill bill : bills) {

            int switchIndex = -1;
            switch (bill.getType()) {
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

            total.get(switchIndex).add(bill.getCost());

            if(minimal.get(switchIndex).compareTo(bill.getCost()) > 0){
                minimal.set(switchIndex, bill.getCost());
            }

            if(maximal.get(switchIndex).compareTo(bill.getCost()) < 0){
                maximal.set(switchIndex, bill.getCost());
            }
            
        }

    }

    private BigDecimal getTotalInCollection(Collection<BigDecimal> data){

        return data.stream().reduce((i, acc) -> acc.add(i)).get();

    }

}
