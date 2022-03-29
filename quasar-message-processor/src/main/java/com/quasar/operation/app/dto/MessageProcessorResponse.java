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
public class MessageProcessorResponse implements Serializable{
    private static final long serialVersionUID = 1L;
    private Position position;
    private String message;
}
