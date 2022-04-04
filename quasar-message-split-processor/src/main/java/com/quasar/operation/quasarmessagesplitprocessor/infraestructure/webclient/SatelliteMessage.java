package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.webclient;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SatelliteMessage implements Serializable{
    private static final long serialVersionUID = -739140134268419671L;
    private String name;
    private Float distance;
    private String[] message;
}
