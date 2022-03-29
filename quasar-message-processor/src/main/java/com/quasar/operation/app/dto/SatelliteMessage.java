package com.quasar.operation.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteMessage implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private Float distance;
    private String[] message;
}
