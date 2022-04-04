package com.quasar.operation.quasarmessagesplitprocessor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "split-processor")
@Getter
@Setter
public class QuasarSplitProcessorConfig {
    private List<String> satellitesNames;
    private String processorName;
    private String messageProcessorBaseUrl;
    private String messageProcessorBaseUri;
}
