package com.quasar.operation.quasarmessagesplitprocessor.application.service;

import com.quasar.operation.quasarmessagesplitprocessor.application.dto.ProcessedMessageDto;
import com.quasar.operation.quasarmessagesplitprocessor.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageProcessorServiceTest {
    @Autowired
    MessageProcessorService messageProcessorService;

    @Test
    public void processMessageTestSuccess(){
        ProcessedMessageDto processedMessageDto = messageProcessorService.processMessage(TestUtils.satellitesInfo());
        assertEquals( "algo espe xxx dato libre",processedMessageDto.getMessage());
    }

}
