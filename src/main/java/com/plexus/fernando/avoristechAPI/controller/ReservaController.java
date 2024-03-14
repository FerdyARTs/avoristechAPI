package com.plexus.fernando.avoristechAPI.controller;


import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;
import com.plexus.fernando.avoristechAPI.persitence.validator.ReservaValidator;
import com.plexus.fernando.avoristechAPI.service.ReservaService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @Autowired
    ReservaValidator reservaValidator;

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getCount(@RequestParam("searchId") String searchId) {
        Optional<ReservaEntity> reserva = reservaService.obtenerReservaPorId(searchId);
        int count = reservaService.getContador();

        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("searchId", searchId);
        response.put("search", reserva);
        response.put("count", count);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<String> search(@RequestBody ReservaDto reserva) {
        try {
            reservaValidator.validarPayload(reserva);
            String idReserva = reservaService.guardarBDyDevolverIdentificador(reserva);

            return ResponseEntity.status(HttpStatus.OK).body(idReserva.toString());
        }catch (ValidationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

