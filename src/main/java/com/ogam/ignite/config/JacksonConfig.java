package com.ogam.ignite.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registrar módulo para Java 8 Date/Time API
        mapper.registerModule(new JavaTimeModule());
        // Configurar para que se serialicen como String en lugar de arrays
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Si deseas un patrón específico global, puedes configurarlo aquí también
        mapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd"));

        return mapper;
    }
}
