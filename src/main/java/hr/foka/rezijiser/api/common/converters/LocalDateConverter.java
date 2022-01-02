package hr.foka.rezijiser.api.common.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class LocalDateConverter implements Converter<String, LocalDate>{
    
    private final DateTimeFormatter formatter;

    public LocalDateConverter(){
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    public LocalDate convert(String value) {
        return LocalDate.from(formatter.parse(value));
    }

}
