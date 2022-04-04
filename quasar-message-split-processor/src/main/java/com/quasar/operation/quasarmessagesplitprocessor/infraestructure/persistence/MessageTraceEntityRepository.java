package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.persistence;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.MessageTrace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTraceEntityRepository extends JpaRepository<MessageTrace, Long> {
}
