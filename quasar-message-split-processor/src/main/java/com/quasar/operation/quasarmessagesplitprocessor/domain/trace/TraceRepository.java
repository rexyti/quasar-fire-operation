package com.quasar.operation.quasarmessagesplitprocessor.domain.trace;

import java.util.List;

public interface TraceRepository {
    public Trace createTrace (String traceId, String satelliteName, Float distance, List<String> message);
}
