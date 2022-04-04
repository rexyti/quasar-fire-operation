package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.controller;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.CreateProcessorTraceCommand;
import com.quasar.operation.quasarmessagesplitprocessor.application.dto.ProcessedMessageDtoPosition;
import com.quasar.operation.quasarmessagesplitprocessor.application.query.MessageProcessedQuery;
import com.quasar.operation.quasarmessagesplitprocessor.config.QuasarSplitProcessorConfig;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.ProcessedMessage;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.exeption.DomainExeption;
import com.quasar.operation.quasarmessagesplitprocessor.infraestructure.controller.dto.MessageProcessorRequest;
import com.quasar.operation.quasarmessagesplitprocessor.infraestructure.controller.dto.MessageProcessorResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/topsecret_split")
public class MessageProcessorSplitController {
    private CommandGateway commandGateway;
    private QueryGateway queryGateway;
    private QuasarSplitProcessorConfig config;

    public MessageProcessorSplitController(CommandGateway commandGateway, QueryGateway queryGateway,QuasarSplitProcessorConfig config){
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.config = config;
    }

    @PostMapping(value = "/{satellite}")
    public ResponseEntity postTopSecretSplit(@PathVariable("satellite") String satellite, @RequestBody MessageProcessorRequest request){
        CreateProcessorTraceCommand command = CreateProcessorTraceCommand.builder()
                .distance(request.getDistance())
                .satelliteName(satellite)
                .processorId(config.getProcessorName())
                .message(request.getMessage())
                .build();
        commandGateway.sendAndWait(command);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<MessageProcessorResponse> getTopSecretSplit(){
        Optional<ProcessedMessage> processedMessage;
        MessageProcessorResponse response = new MessageProcessorResponse();
        try {
            processedMessage = queryGateway.query(new MessageProcessedQuery(), ResponseTypes.optionalInstanceOf(ProcessedMessage.class)).get();
            ProcessedMessage message = processedMessage.orElseThrow(()->new RuntimeException("No message processed"));
            response.setMessage(Optional.ofNullable(message.getMessage()).orElseThrow(()->new DomainExeption("null message")));
            ProcessedMessageDtoPosition position = new ProcessedMessageDtoPosition();
            position.setX(message.getX());
            position.setY(message.getY());
            response.setPosition(position);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Cannot process the message");
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }

    }

}
