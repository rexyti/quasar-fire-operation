package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.webclient;

import com.quasar.operation.quasarmessagesplitprocessor.application.dto.ProcessedMessageDto;
import com.quasar.operation.quasarmessagesplitprocessor.application.dto.ProcessedMessageDtoPosition;
import com.quasar.operation.quasarmessagesplitprocessor.application.service.MessageProcessorService;
import com.quasar.operation.quasarmessagesplitprocessor.config.QuasarSplitProcessorConfig;
import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.ProcessorTrace;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.exeption.DomainExeption;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessageProcessorWebClient implements MessageProcessorService {

    private QuasarSplitProcessorConfig config;

    public MessageProcessorWebClient (QuasarSplitProcessorConfig config){
        this.config = config;
    }

    @Override
    public ProcessedMessageDto processMessage(Map<String, ProcessorTrace> satellitesInfo) throws DomainExeption {
        WebClient webClient = WebClient.create(config.getMessageProcessorBaseUrl());

        MessageProcessorPostRequest request = createRequestBody(satellitesInfo);

        MessageProcessorPostResponse response = webClient.post()
                .uri(config.getMessageProcessorBaseUri())
                .body(Mono.just(request), MessageProcessorPostRequest.class)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        responseError -> responseError.bodyToMono(String.class).map(DomainExeption::new))
                .onStatus(
                        HttpStatus::is4xxClientError,
                        responseError -> responseError.bodyToMono(String.class).map(DomainExeption::new))
                .bodyToMono(MessageProcessorPostResponse.class)
                .block();

        return mapToProcessedMessageDto(response);
    }

    private MessageProcessorPostRequest createRequestBody(Map<String, ProcessorTrace> satellitesInfo){
        MessageProcessorPostRequest messageProcessorPostRequest = new MessageProcessorPostRequest();

        messageProcessorPostRequest.setSatellites(
            satellitesInfo.values()
                .stream()
                .map(trace -> new SatelliteMessage(trace.getSatelliteName(), trace.getDistance(),trace.getMessage().toArray(new String[trace.getMessage().size()])))
                .collect(Collectors.toList()));

        return messageProcessorPostRequest;
    }

    private ProcessedMessageDto mapToProcessedMessageDto(MessageProcessorPostResponse messageProcessorPostResponse){
        ProcessedMessageDto processedMessageDto = new ProcessedMessageDto();
        processedMessageDto.setMessage(messageProcessorPostResponse.getMessage());

        ProcessedMessageDtoPosition position= new ProcessedMessageDtoPosition();
        position.setX(messageProcessorPostResponse.getPosition().getX());
        position.setY(messageProcessorPostResponse.getPosition().getY());

        processedMessageDto.setPosition(position);
        return processedMessageDto;
    }

}
