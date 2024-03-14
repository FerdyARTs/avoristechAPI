package com.plexus.fernando.avoristechAPI.persitence.config;

import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaSearchDto;
import com.plexus.fernando.avoristechAPI.persitence.mapper.ReservaMapper;
import com.plexus.fernando.avoristechAPI.service.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KakfaListener {

    @Autowired
    ReservaService reservaService;
    @Autowired
    ReservaMapper reservaMapper;

    private static final Logger log = LoggerFactory.getLogger(KakfaListener.class);

    @KafkaListener(topics = "hotel_availability_searches", groupId = "group")
    public void listen(String reservaDto) {
        log.info("Mensaje Recibido: {}", reservaDto);

//        reservaService.crearReserva(this.reservaMapper.toEntity(reservaDto));
    }
}
