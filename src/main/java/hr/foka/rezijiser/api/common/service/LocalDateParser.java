package hr.foka.rezijiser.api.common.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class LocalDateParser {
    
    private final DateTimeFormatter formatter;

    public LocalDateParser(){
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public LocalDate parseString(String date){
        return LocalDate.from(formatter.parse(date));
    }

}
