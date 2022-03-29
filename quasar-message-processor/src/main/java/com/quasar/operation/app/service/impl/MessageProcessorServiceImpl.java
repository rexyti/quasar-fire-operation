package com.quasar.operation.app.service.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.quasar.operation.app.config.MessageProcessorConfig;
import com.quasar.operation.app.dto.MessageProcessorRequest;
import com.quasar.operation.app.dto.MessageProcessorResponse;
import com.quasar.operation.app.dto.Position;
import com.quasar.operation.app.dto.SatelliteMessage;
import com.quasar.operation.app.service.IMessageProcessorService;
import com.quasar.operation.app.utils.mapper.MessageProcessorMapper;
import com.quasar.operation.message.processor.MessageProcessor;
import com.quasar.operation.message.processor.Point;
import com.quasar.operation.message.processor.exceptions.MessageProcessorExeption;

import org.springframework.stereotype.Service;

@Service
public class MessageProcessorServiceImpl implements IMessageProcessorService {

    private MessageProcessorConfig config;
    
    private MessageProcessorMapper mapper;

    private final MessageProcessor messageProcessor;

  
    public MessageProcessorServiceImpl(MessageProcessorConfig config,MessageProcessorMapper mapper) {
        this.config = config;
        this.mapper = mapper;

        List<Position> satellites =  Arrays.asList(new Position[config.getSatellitesPosition().size()]);
        config.getSatellitesIndex().entrySet().stream()
            .forEach( set -> satellites.set(set.getValue(), config.getSatellitesPosition().get(set.getKey())));
        this.messageProcessor=new MessageProcessor(mapper.listOfPositionToListOfPoint(satellites), config.getTolerance());
    }

    @Override
    public MessageProcessorResponse processRequest(MessageProcessorRequest request) throws MessageProcessorExeption{
        MessageProcessorResponse response = new MessageProcessorResponse();
        response.setPosition(processPosition(request));
        response.setMessage(processMessage(request));
        return response;
    }

    
    private Position processPosition(MessageProcessorRequest request){
        Point point = messageProcessor.getLocation(mapToDistancesArray(request));
        return mapper.pointToPosition(point);
    }

    private String processMessage(MessageProcessorRequest request){
        return messageProcessor.getMessage(mapToMessageArray(request));
    }

    private String[][] mapToMessageArray(MessageProcessorRequest request){
        return request.getSatellites().stream()
            .map(SatelliteMessage::getMessage)
            .toArray(String[][]::new);
    }


    private Float[] mapToDistancesArray(MessageProcessorRequest request){
        return request.getSatellites().stream()
            .map(satellite-> {
                satellite.setName(config.getSatellitesIndex().get(satellite.getName()).toString());
                return satellite;
            })
            .sorted(Comparator.comparing(SatelliteMessage::getName))
            .map(SatelliteMessage::getDistance)
            .toArray(Float[]::new);
    }
}
