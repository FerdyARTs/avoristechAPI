package com.plexus.fernando.avoristechAPI.persitence.validator.impl;

import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.validator.ReservaValidator;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class ReservaValidatorImpl implements ReservaValidator {

    public boolean validarPayload(ReservaDto reservaDto){
        try {
            this.validatorHotelId(reservaDto.getHotelId());
            this.validateDates(reservaDto.getCheckIn(),reservaDto.getCheckOut());
            this.validateYears(reservaDto.getEdades());
            return true;
        } catch (ValidationException e) {
            throw new ValidationException("Error de validación: " + e.getMessage());
        }
    }

    private boolean validatorHotelId(String hotelId) {
        try{
        return hotelId != null && hotelId.length() == 7;
        }catch (Exception e){
            throw new ValidationException("El ID del hotel debe tener exactamente 7 caracteres.");
        }
    }

    private void validateDates(OffsetDateTime checkIn, OffsetDateTime checkOut) throws ValidationException {
        if (checkIn == null || checkOut == null || checkIn.isAfter(checkOut)) {
            throw new ValidationException("Las fechas de check-in y check-out no son correctas.");
        }
    }

    private void validateYears(List<Integer> years) throws ValidationException {
        for (Integer year : years) {
            if (year == null || year < 0 || year > 99) {
                throw new ValidationException("Uno o más años en la lista no están en el rango válido (0-99).");
            }
        }
    }
}
