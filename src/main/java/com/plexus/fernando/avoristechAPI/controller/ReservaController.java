package com.plexus.fernando.avoristechAPI.controller;


import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.mapper.ReservaMapper;
import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;
import com.plexus.fernando.avoristechAPI.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    ReservaMapper reservaMapper;

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getCount(@RequestParam("searchId") Long searchId) {
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
    public ResponseEntity<Long> search(@RequestBody ReservaDto reserva) {
        Long idReserva = reservaService.devolverIdentificador(reserva);
        return ResponseEntity.ok(idReserva);
    }
}

