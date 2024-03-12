package com.plexus.fernando.avoristechAPI.persitence.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Reservas")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchId;

    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "check_in")
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

    @ElementCollection
    @CollectionTable(name = "edades", joinColumns = @JoinColumn(name = "reserva_id"))
    @Column(name = "edad")
    private List<Integer> edades;

    public ReservaEntity() {
    }

    public ReservaEntity(Long searchId, String hotelId, Date checkIn, Date checkOut, List<Integer> edades) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.edades = edades;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }


    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public List<Integer> getEdades() {
        return edades;
    }

    public void setEdades(List<Integer> edades) {
        this.edades = edades;
    }
}
