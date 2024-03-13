package com.plexus.fernando.avoristechAPI.persitence.mapper;

import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaSearchDto;
import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class ReservaMapper {
    public static ReservaEntity toEntity(ReservaSearchDto dto) {
        ReservaEntity entity = new ReservaEntity();
        entity.setSearchId(dto.getSearchId());
        entity.setHotelId(dto.getHotelId());
        entity.setCheckIn(convertOffsetDateTimeToDate(dto.getCheckIn()));
        entity.setCheckOut(convertOffsetDateTimeToDate(dto.getCheckOut()));
        entity.setEdades(dto.getEdades());
        return entity;
    }

    private static Date convertOffsetDateTimeToDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? Date.from(offsetDateTime.toInstant()) : null;
    }
}
