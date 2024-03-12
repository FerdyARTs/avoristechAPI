package com.plexus.fernando.avoristechAPI.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaDto;
import com.plexus.fernando.avoristechAPI.persitence.config.KakfaListener;
import com.plexus.fernando.avoristechAPI.persitence.model.ReservaEntity;

import com.plexus.fernando.avoristechAPI.persitence.repository.ReservaRepository;
import com.plexus.fernando.avoristechAPI.service.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    KafkaTemplate<String, ReservaDto> kafkaTemplate;

    private AtomicInteger count;

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

    public Long devolverIdentificador(ReservaDto reserva){
        kafkaTemplate.send("hotel_availability_searches", reserva);
        return null;
    }

    public Optional<ReservaEntity> obtenerReservaPorId(Long searchId) {
        count.incrementAndGet();
        return reservaRepository.findById(searchId);
    }

    public int getContador() {
        return count.get();
    }
}
