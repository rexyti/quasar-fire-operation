package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.persistence;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.ProcessedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedMessageEntityRepository extends JpaRepository<ProcessedMessage,Long> {
    public ProcessedMessage findFirstByOrderByCreationDateDesc();
}
