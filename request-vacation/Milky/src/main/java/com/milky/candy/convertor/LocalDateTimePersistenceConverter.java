package com.milky.candy.convertor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        return (entityValue == null) ? null : Timestamp.valueOf(entityValue);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
        if (databaseValue == null)
            return null;

        return LocalDateTime.ofInstant(databaseValue.toInstant(), ZoneId.systemDefault());
    }
}
