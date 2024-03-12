package com.plexus.fernando.avoristechAPI.persitence.Dto;

import java.time.OffsetDateTime;
import java.util.List;

public class ReservaDto {
    private String hotelId;
    private OffsetDateTime checkIn;

    private OffsetDateTime checkOut;

    private List<Integer> edades;

    public ReservaDto() {
    }

    public ReservaDto(String hotelId, OffsetDateTime checkIn, OffsetDateTime checkOut, List<Integer> edades) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.edades = edades;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public OffsetDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(OffsetDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public OffsetDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(OffsetDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public List<Integer> getEdades() {
        return edades;
    }

    public void setEdades(List<Integer> edades) {
        this.edades = edades;
    }
}
