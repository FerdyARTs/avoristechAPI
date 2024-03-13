package com.plexus.fernando.avoristechAPI.persitence.config;

import com.plexus.fernando.avoristechAPI.persitence.Dto.ReservaSearchDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServers;

    //Consumer
    @Bean
    public Map<String, Object> consumerProps(){
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"group");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"60000");
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.plexus.fernando.avoristechAPI.persitence.Dto");

        return props;

    }

    @Bean
    public ConsumerFactory<String, ReservaSearchDto>consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,ReservaSearchDto> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,ReservaSearchDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    //Producer

    private Map<String, Object> producerProps(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG,0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG,1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    @Bean
    public KafkaTemplate<String, ReservaSearchDto> createTemplate(){
        Map<String, Object> configProps = producerProps();
        ProducerFactory<String, ReservaSearchDto> pf = new DefaultKafkaProducerFactory<String, ReservaSearchDto>(configProps);

        KafkaTemplate<String, ReservaSearchDto> template = new KafkaTemplate<>(pf);

        return template;
    }
}
