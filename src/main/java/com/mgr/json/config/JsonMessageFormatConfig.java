package com.mgr.json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

/**
 * Klasa zawierajaca beana mapujacego format JSON na obiekt javowy
 */
public class JsonMessageFormatConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
