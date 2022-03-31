package com.quasar.operation.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageProcessorError implements Serializable{
    private static final long serialVersionUID = 1L;
    private String message;
}
