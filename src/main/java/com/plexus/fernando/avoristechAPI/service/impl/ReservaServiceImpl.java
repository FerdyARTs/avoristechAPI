package com.plexus.fernando.avoristechAPI.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaSearchDto;


import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;

import com.plexus.fernando.avoristechAPI.persitence.repository.ReservaRepository;
import com.plexus.fernando.avoristechAPI.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ReservaServiceImpl implements ReservaService {

    @Value("${spring.kafka.topic}")
    String topic;
    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    private AtomicInteger count;

    private ObjectMapper mapper = new ObjectMapper();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 7;


    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
        this.count = new AtomicInteger(0);
    }

    public ReservaEntity crearReserva(ReservaEntity reserva) {
        ReservaEntity nuevaReserva = reservaRepository.save(reserva);
        return nuevaReserva;
    }

    public String guardarBDyDevolverIdentificador(ReservaDto reserva){
        String searchId = this.generarSearchId();
        ReservaSearchDto reservaSearchDto = new ReservaSearchDto(searchId,reserva.getHotelId(),reserva.getCheckIn(),reserva.getCheckOut(),reserva.getEdades());
        try {
            kafkaTemplate.send("hotel_availability_searches",mapper.writeValueAsString(reservaSearchDto));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error al comunicarnos con Kakfa");
        }
        return searchId;
    }

    public Optional<ReservaEntity> obtenerReservaPorId(String searchId) {
        count.incrementAndGet();
        return reservaRepository.findById(searchId);
    }

    public int getContador() {
        return count.get();
    }

    private String generarSearchId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString().toString();
    }
}
