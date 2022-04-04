package com.quasar.operation.quasarmessagesplitprocessor.util;

import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessagePosition;
import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.Processor;
import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.ProcessorTrace;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.CreateProcessorTraceCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.InitializeProcessorCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.ProcessMessageCommant;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.MessageProcessedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.ProcessorInitializedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.ProcessorPreparedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceReceivedDomainEvent;

import java.util.*;

public class TestUtils {
    public static final String processorId = UUID.randomUUID().toString();

    public static final List<String> satellitesNames = Arrays.asList("sato","kenobi","skywalker");

    public static final List<String> satoMessage = Arrays.asList("algo", "", "", "dato", "");
    public static final List<String> kenobiMessage = Arrays.asList("","","","","","algo", "", "xxx", "", "");
    public static final List<String> skywalkerMessage = Arrays.asList("", "espe", "", "", "libre");

    public static Map<String, ProcessorTrace> satellitesInfo(){
        ProcessorTrace trace = new ProcessorTrace(
                "sato"
                , 473.814309619f
                , satoMessage);
        ProcessorTrace trace1 = new ProcessorTrace(
                "skywalker"
                , 156.524758425f
                , skywalkerMessage);
        ProcessorTrace trace2 = new ProcessorTrace(
                "kenobi"
                , 581.807528312f
                , kenobiMessage);
        Map<String,ProcessorTrace> satellitesInfo = new HashMap<String,ProcessorTrace>();
        satellitesInfo.put("sato",trace);
        satellitesInfo.put("skywalker",trace1);
        satellitesInfo.put("kenobi",trace2);
        return satellitesInfo;
    }


    public static InitializeProcessorCommand initializeProcessorCommand(){
        return new InitializeProcessorCommand(processorId,satellitesNames);
    }
    public static ProcessorInitializedDomainEvent processorInitializedDomainEvent(){
        return new ProcessorInitializedDomainEvent(processorId, satellitesNames);
    }
    public static CreateProcessorTraceCommand createProcessorTraceCommandSato(){
        return  new CreateProcessorTraceCommand(processorId,"sato",473.814309619f, satoMessage);
    }

    public static TraceReceivedDomainEvent traceReceivedDomainEventSato(){

        return  new TraceReceivedDomainEvent(processorId, satellitesInfo().get("sato"));
    }

    public static TraceReceivedDomainEvent traceReceivedDomainEventSkywalker(){

        return  new TraceReceivedDomainEvent(processorId, satellitesInfo().get("skywalker"));
    }

    public static TraceReceivedDomainEvent traceReceivedDomainEventKenobi(){

        return  new TraceReceivedDomainEvent(processorId, satellitesInfo().get("kenobi"));
    }

    public static ProcessorPreparedDomainEvent processorPreparedDomainEvent(){
        Map<String, ProcessorTrace> satellitesInfo;
        return new ProcessorPreparedDomainEvent(processorId,satellitesInfo());
    }

    public static ProcessMessageCommant processMessageCommant(){
        MessagePosition position = new MessagePosition();
        position.setX(1.111f);
        position.setX(2.222f);
        return new ProcessMessageCommant(processorId, "this is the message",position, Processor.ProcessorStatus.SUCCESS);
    }

    public static MessageProcessedDomainEvent messageProcessedDomainEvent(){
        MessagePosition position = new MessagePosition();
        position.setX(1.111f);
        position.setX(2.222f);
        return new MessageProcessedDomainEvent(processorId, "this is the message",position, Processor.ProcessorStatus.SUCCESS);
    }

}
