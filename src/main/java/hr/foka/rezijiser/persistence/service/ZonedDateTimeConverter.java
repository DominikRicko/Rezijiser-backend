package hr.foka.rezijiser.persistence.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements 
    javax.persistence.AttributeConverter<java.time.ZonedDateTime, java.sql.Timestamp>, 
    org.springframework.core.convert.converter.Converter<String, ZonedDateTime> {

    private final DateTimeFormatter formatter;

    public ZonedDateTimeConverter(){
        this.formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    }

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
        if (entityValue == null)
            return null;
        return Timestamp.from(entityValue.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
        LocalDateTime localDateTime = databaseValue.toLocalDateTime();
        return localDateTime.atZone(ZoneId.systemDefault());
    }

    @Override
    public ZonedDateTime convert(String source) {
        return ZonedDateTime.from(formatter.parse(source));
    }
}