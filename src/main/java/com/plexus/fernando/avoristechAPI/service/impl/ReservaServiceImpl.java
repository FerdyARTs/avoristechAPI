package com.plexus.fernando.avoristechAPI.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaSearchDto;
import com.plexus.fernando.avoristechAPI.persitence.config.KakfaListener;
import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;

import com.plexus.fernando.avoristechAPI.persitence.repository.ReservaRepository;
import com.plexus.fernando.avoristechAPI.service.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


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
    KafkaTemplate<String, ReservaSearchDto> kafkaTemplate;

    private AtomicInteger count;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 5;

    private static final Logger log = LoggerFactory.getLogger(KakfaListener.class);
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
        this.count = new AtomicInteger(0);
    }

    public ReservaEntity crearReserva(ReservaEntity reserva) {
        log.info("LLegamos al create con lo siguiente: {}", reserva);
        ReservaEntity nuevaReserva = reservaRepository.save(reserva);
        return nuevaReserva;
    }

    public String guardarBDyDevolverIdentificador(ReservaDto reserva){
        String searchId = this.generarSearchId();
        ReservaSearchDto reservaSearchDto = new ReservaSearchDto(searchId,reserva.getHotelId(),reserva.getCheckIn(),reserva.getCheckOut(),reserva.getEdades());
        kafkaTemplate.send(topic, reservaSearchDto);
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
