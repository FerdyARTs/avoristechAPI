package com.plexus.fernando.avoristechAPI.persitence.Dto;

import java.time.OffsetDateTime;
import java.util.List;

public class ReservaDto {
    private final String hotelId;
    private final OffsetDateTime checkIn;

    private final OffsetDateTime checkOut;

    private final List<Integer> edades;


    public ReservaDto(String hotelId, OffsetDateTime checkIn, OffsetDateTime checkOut, List<Integer> edades) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.edades = edades;
    }

    public String getHotelId() {
        return hotelId;
    }

    public OffsetDateTime getCheckIn() {
        return checkIn;
    }

    public OffsetDateTime getCheckOut() {
        return checkOut;
    }

    public List<Integer> getEdades() {
        return edades;
    }
}
