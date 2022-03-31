package com.quasar.operation.app.config;

import java.util.Map;

import com.quasar.operation.app.dto.Position;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;


@Configuration
@ConfigurationProperties(prefix = "message-processor")
@Getter
@Setter
public class MessageProcessorConfig {
    private Float tolerance;
    private Map<String,Integer> satellitesIndex;
    private Map<String,Position> satellitesPosition;
}
