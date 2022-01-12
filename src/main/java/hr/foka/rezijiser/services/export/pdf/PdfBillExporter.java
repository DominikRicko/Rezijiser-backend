package hr.foka.rezijiser.services.export.pdf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.services.export.resource.ExportResource;

@Service
public class PdfBillExporter extends AbstractPdfExporter<Bill> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfBillExporter.class);

    public static final String KEY_STARTING_DATE = "pdf_starting_date";
    public static final String KEY_ENDING_DATE = "pdf_ending_date";

    @Override
    protected Boolean export(Document document, ExportResource<Bill> bills) throws DocumentException {

        Collection<Bill> data = bills.getData().stream().sorted(this::sortCollectionByPayday)
                .collect(Collectors.toList());

        document.addAuthor("Rezijiser");

        LOGGER.info("Generating report page.");
        generateReportPage(document, bills);

        LOGGER.info("Generating bill pages");
        generateBillPage(document, data, Type.POWER);
        generateBillPage(document, data, Type.WATER);
        generateBillPage(document, data, Type.GAS);
        generateBillPage(document, data, Type.RESERVATION);
        generateBillPage(document, data, Type.TRASH);
        generateBillPage(document, data, Type.COMMUNAL);
        generateBillPage(document, data, Type.HRT);
        generateBillPage(document, data, Type.TELECOMMUNICATION);
        
        return true;

    }

    private void generateReportPage(Document document, ExportResource<Bill> bills) throws DocumentException{

        LocalDate startingDate = (LocalDate) bills.getMetadata().get(KEY_STARTING_DATE);
        LocalDate endingDate = (LocalDate) bills.getMetadata().get(KEY_ENDING_DATE);
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        document.addTitle(String.format("Izvještaj o režijima između %s i %s", startingDate.toString(), endingDate.toString()));

        BigDecimal totalCost = getTotalInCollection(bills.getData().stream().map(Bill::getCost).collect(Collectors.toList()));
        StringBuilder builder = new StringBuilder();
        builder.append("U periodu od ").append(startingDate.toString()).append(" do ").append(endingDate.toString());
        builder.append("su sveukupne režije iznosile ").append(totalCost.toString()).append(" HRK.");

        Chunk chunk = new Chunk(builder.toString(), font);
        document.add(chunk);

        List<BigDecimal> total = new ArrayList<>();
        List<BigDecimal> minimal = new ArrayList<>();
        List<BigDecimal> maximal = new ArrayList<>();

        findStats(bills.getData(), total, minimal, maximal);

        List<String> totalString = total.stream().map(BigDecimal::toString).collect(Collectors.toList());
        totalString.add(0, "Ukupno plaćeni iznos");

        List<String> minimalString = minimal.stream().map(BigDecimal::toString).collect(Collectors.toList());
        minimalString.add(0, "Najmanji plaćeni iznos");

        List<String> maximalString = maximal.stream().map(BigDecimal::toString).collect(Collectors.toList());
        maximalString.add(0, "Najveći plaćeni iznos");

        PdfPTable table = new PdfPTable(9);
        addTableHeaders(table, Arrays.asList("", "Struja", "Voda", "Plin", "Pričuva", "Smeće", "Komunalac", "HRT", "Telekom"));
        addRow(table, totalString);
        addRow(table, minimalString);
        addRow(table, maximalString);

        document.add(table);

    }

    private void generateBillPage(Document document, Collection<Bill> bills, Type type) throws DocumentException {
        bills = bills.stream().filter(it -> it.getType() == type).collect(Collectors.toList());
        document.newPage();

        document.addHeader(type.name(), type.name());
        PdfPTable table = new PdfPTable(4);
        addTableHeaders(table, Arrays.asList("Datum dospijeća", "Datum plaćanja", "Iznos plaćanja", "Potrošnja"));

        for(Bill bill : bills) {
            addRow(table, bill);
        }

        document.add(table);

    }

    private void addTableHeaders(PdfPTable table, Collection<String> headers) {
        for(String header : headers){
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setBorderWidth(2);
            headerCell.setPhrase(new Phrase(header));
            table.addCell(headerCell);
        }
    }

    private void addRow(PdfPTable table, Collection<String> data) {
        for(String cellData : data){
            table.addCell(cellData);
        }
    }

    private void addRow(PdfPTable table, Bill bill){
        table.addCell(bill.getPayday().toString());
        if(bill.getDatePaid() != null) 
            table.addCell(bill.getDatePaid().toString());
        else 
            table.addCell("NIJE PLAĆENO");

        table.addCell(bill.getCost().toString());
        if(bill.getSpent() != null)
            table.addCell(bill.getSpent().toString());
        else 
            table.addCell("");
        
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

            if (minimalMap.get(bill.getType()) != null) {
                if (minimalMap.get(bill.getType()).compareTo(bill.getCost()) > 0) {
                    minimalMap.put(bill.getType(), bill.getCost());
                }
            } else {
                minimalMap.put(bill.getType(), bill.getCost());
            }

            if (maximalMap.get(bill.getType()) != null) {
                if (maximalMap.get(bill.getType()).compareTo(bill.getCost()) < 0) {
                    maximalMap.put(bill.getType(), bill.getCost());
                }
            } else {
                maximalMap.put(bill.getType(), bill.getCost());
            }

            sumMap.put(bill.getType(), sumMap.get(bill.getType()).add(bill.getCost()));

        }

        for (Bill.Type type : Bill.Type.values()) {

            int switchIndex = -1;
            switch (type) {
                case POWER:
                    switchIndex = 0;
                    break;
                case WATER:
                    switchIndex = 1;
                    break;
                case GAS:
                    switchIndex = 2;
                    break;
                case RESERVATION:
                    switchIndex = 3;
                    break;
                case TRASH:
                    switchIndex = 4;
                    break;
                case COMMUNAL:
                    switchIndex = 5;
                    break;
                case HRT:
                    switchIndex = 6;
                    break;
                case TELECOMMUNICATION:
                    switchIndex = 7;
                    break;
                default:
                    continue;
            }

            maximal.set(switchIndex, maximalMap.get(type));
            minimal.set(switchIndex, minimalMap.get(type));
            total.set(switchIndex, sumMap.get(type));

        }

    }

    private BigDecimal getTotalInCollection(Collection<BigDecimal> data) {

        return data.stream().reduce((i, acc) -> acc.add(i)).get();

    }

    private int sortCollectionByPayday(Bill bill1, Bill bill2) {
        return bill1.getPayday().compareTo(bill2.getPayday());
    }

}
