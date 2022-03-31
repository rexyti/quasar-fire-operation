package com.quasar.operation.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageProcessorRequest implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<SatelliteMessage> satellites;
}
