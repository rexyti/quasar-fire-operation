package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.webclient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageProcessorPostRequest implements Serializable {

    private static final long serialVersionUID = 3707654705815936269L;

    private List<SatelliteMessage> satellites;

}
