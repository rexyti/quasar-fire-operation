package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.webclient;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position implements Serializable{
    private static final long serialVersionUID = -8472228778993810278L;
    private Float x;
    private Float y;
}
