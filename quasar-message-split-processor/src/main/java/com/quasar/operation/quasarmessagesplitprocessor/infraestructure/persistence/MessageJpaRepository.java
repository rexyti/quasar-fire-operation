package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.persistence;

import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessagePosition;
import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessageRepository;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.ProcessedMessage;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class MessageJpaRepository implements MessageRepository {

    private ProcessedMessageEntityRepository messageEntityRepository;

    public MessageJpaRepository(ProcessedMessageEntityRepository messageEntityRepository){
        this.messageEntityRepository = messageEntityRepository;
    }

    @Override
    public void createMessage(String messageId, String finalMessage, MessagePosition position, String status) {
        ProcessedMessage processedMessage = new ProcessedMessage();
        processedMessage.setUid(messageId);
        processedMessage.setMessage(finalMessage);
        if (Optional.ofNullable(position).isPresent()){
            processedMessage.setX(position.getX());
            processedMessage.setY(position.getY());
        }
        processedMessage.setStatus(status);
        processedMessage.setCreationDate(new Date());
        messageEntityRepository.save(processedMessage);
    }

    @Override
    public Optional<ProcessedMessage> getLastMessage() {
        return Optional.ofNullable(messageEntityRepository.findFirstByOrderByCreationDateDesc());
    }
}
