/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.db;

import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author danielschuelke
 */
@Converter(autoApply = true)
public class LocalTimePersistenceConverter implements AttributeConverter<LocalTime, Time> {
  // mapping with java.util.Calendar breaks EclipseLink

  @Override
  public Time convertToDatabaseColumn(LocalTime attribute) {
    if (attribute == null) {
      return null;
    }
    return Time.valueOf(attribute);
  }

  @Override
  public LocalTime convertToEntityAttribute(Time dbData) {
    if (dbData == null) {
      return null;
    }

    return dbData.toLocalTime();
  }

}