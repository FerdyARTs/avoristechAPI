package com.plexus.fernando.avoristechAPI.service;

import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;

import java.util.Optional;

public interface ReservaService {
    ReservaEntity crearReserva(ReservaEntity reserva);

    String guardarBDyDevolverIdentificador(ReservaDto reserva);

    Optional<ReservaEntity> obtenerReservaPorId(String searchId);

    int getContador();
}
